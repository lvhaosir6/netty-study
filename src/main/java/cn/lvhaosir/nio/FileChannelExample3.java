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
public class FileChannelExample3 {

    public static void main(String[] args) throws IOException {
        File file = new File("E:\\2019-project\\netty-study\\src\\main\\java\\cn\\lvhaosir\\nio\\1.txt");
        FileInputStream inputStream = new FileInputStream(file);
        FileChannel inChannel = inputStream.getChannel();
        FileOutputStream outputStream = new FileOutputStream("E:\\2019-project\\netty-study\\src\\main\\java\\cn\\lvhaosir\\nio\\2.txt");
        FileChannel outChannel = outputStream.getChannel();
        ByteBuffer allocate = ByteBuffer.allocate(2);
        while (true) {
            allocate.clear();
            int read = inChannel.read(allocate);
            if (read == -1) {
                break;
            }
            allocate.flip();
            System.out.println(new String(allocate.array()));
            outChannel.write(allocate);
        }
        inputStream.close();
        outputStream.close();
    }
}
