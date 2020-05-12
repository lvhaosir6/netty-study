package cn.lvhaosir.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author lvhaosir 2020/2/25 15:29
 * @Description: TODO()
 * @Version v1.0
 **/
public class FileChannelExample1 {


    public static void main(String[] args) throws IOException {
        String str = "Hello NIO";
        FileOutputStream outputStream = new FileOutputStream("E:\\2019-project\\netty-study\\src\\main\\java\\cn\\lvhaosir\\nio\\1.txt");
        FileChannel channel = outputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(str.getBytes());
        byteBuffer.flip();
        channel.write(byteBuffer);
    }

}
