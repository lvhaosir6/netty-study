package cn.lvhaosir.nio.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.net.Socket;

/**
 * @author lvhaosir 2020/3/16 19:46
 * @Description: TODO()
 * @Version v1.0
 **/
public class OldIOClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 7001);
        String fileName = "1.xlsx";
        FileInputStream inputStream = new FileInputStream(fileName);
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        byte[] buffer = new byte[4096];
        long startTime = System.currentTimeMillis();
        long total = 0;
        while (true) {
            long readCount = inputStream.read(buffer);
            if (readCount < 0) {
                break;
            }
            total += readCount;
            dataOutputStream.write(buffer);
        }
        System.out.println("发送总字节： " + total + " ，总耗时： " + (System.currentTimeMillis() - startTime));
        dataOutputStream.close();
        socket.close();
        inputStream.close();

    }
}
