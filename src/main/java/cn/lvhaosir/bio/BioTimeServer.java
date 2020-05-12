package cn.lvhaosir.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lvhaosir 2019/11/22 23:17
 * @Description: TODO()
 * @Version v1.0
 **/
public class BioTimeServer {


    public static void main(String[] args) throws IOException {

        int port = 8080;

        ServerSocket server = null;

        try {
            server = new ServerSocket(port);
            System.out.println("The bio time server is start int port : " + port);
            Socket socket = null;
            while (true) {
                socket = server.accept();
                System.out.println("connect client");
                new Thread(new BioTimeServerHandler(socket)).start();
            }
        } finally {
            if (server != null) {
                System.out.println("The bio time server close");
                server.close();
                server = null;
            }
        }

    }

}
