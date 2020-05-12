package cn.lvhaosir.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author lvhaosir 2020/2/26 11:45
 * @Description: TODO()
 * @Version v1.0
 **/
public class FileChannelExample4 {

    public static void main(String[] args) throws IOException {

        FileInputStream inputStream = new FileInputStream("E:\\2019-project\\netty-study\\src\\main\\java\\cn\\lvhaosir\\nio\\a.JPG");

        FileOutputStream outputStream = new FileOutputStream("E:\\2019-project\\netty-study\\src\\main\\java\\cn\\lvhaosir\\nio\\b.png");

        FileChannel inputStreamChannel = inputStream.getChannel();
        FileChannel outputStreamChannel = outputStream.getChannel();

        outputStreamChannel.transferFrom(inputStreamChannel,0,inputStreamChannel.size());

        inputStreamChannel.close();
        outputStreamChannel.close();
        inputStream.close();
        outputStream.close();

    }

}
