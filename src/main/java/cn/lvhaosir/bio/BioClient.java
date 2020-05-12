package cn.lvhaosir.bio;

import java.io.*;
import java.net.Socket;

/**
 * @author lvhaosir 2019/11/23 0:12
 * @Description: TODO()
 * @Version v1.0
 **/
public class BioClient {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1",8080);

        InputStream inputStream = socket.getInputStream();

        OutputStream outputStream = socket.getOutputStream();

        PrintWriter out = new PrintWriter(outputStream,true);
        out.println("Hello world");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        System.out.println("receive msg : "+s);
    }

}
