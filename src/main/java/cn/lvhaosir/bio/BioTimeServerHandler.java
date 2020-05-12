package cn.lvhaosir.bio;

import java.io.*;
import java.net.Socket;

/**
 * @author lvhaosir 2019/11/22 23:25
 * @Description: TODO()
 * @Version v1.0
 **/
public class BioTimeServerHandler implements Runnable {

    private Socket socket;

    public BioTimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        try {
            byte[] bytes = new byte[1024];
            inputStream = this.socket.getInputStream();
            while (true) {
                int read = inputStream.read(bytes);
                if (read != -1) {
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (Exception e) {

        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
