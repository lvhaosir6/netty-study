package cn.lvhaosir.nio.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @author lvhaosir 2020/3/16 20:26
 * @Description: TODO()
 * @Version v1.0
 **/
public class NewIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1",7002));
        String fileName = "1.xlsx";
        FileChannel fileChannel = new FileInputStream(fileName).getChannel();
        long startTime = System.currentTimeMillis();
        /**
         * 在Linux下transferTo方法就可以完成传输
         * 在windows下，一次transferTo只能发送 8M ，需要分段传输
         */
        long size = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
        System.out.println("发送总字节： " + size + " ，总耗时： " + (System.currentTimeMillis() - startTime));
    }
}
