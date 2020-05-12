package cn.lvhaosir.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author lvhaosir 2020/2/26 11:45
 * @Description: TODO()
 * @Version v1.0
 **/
public class FileChannelExample2 {

    public static void main(String[] args) throws IOException {
        File file = new File("E:\\2019-project\\netty-study\\src\\main\\java\\cn\\lvhaosir\\nio\\1.txt");
        FileInputStream inputStream = new FileInputStream(file);
        FileChannel channel = inputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        channel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array()));
        inputStream.close();
    }

}
