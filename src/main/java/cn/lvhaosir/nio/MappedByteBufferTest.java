package cn.lvhaosir.nio;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author lvhaosir 2020/3/6 21:01
 * @Description: TODO()
 * @Version v1.0
 **/
public class MappedByteBufferTest {
    /**
     *  MappedByteBuffer
     *      可以让文件直接在（堆外内存）内存修改，操作系统不需要再改变一次。
     * @param args
     */

    public static void main(String[] args) throws Exception {

        RandomAccessFile randomAccessFile = new RandomAccessFile("E:\\2019-project\\netty-study\\src\\main\\java\\cn\\lvhaosir\\nio\\1.txt", "rw");
        FileChannel channel = randomAccessFile.getChannel();

        /**
         * 1. 读写模式
         * 2. 可以直接修改的起始位置
         * 3. 映射到内存的大小
         */
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0, (byte) 'L');
        mappedByteBuffer.put(3,(byte) '9');
        randomAccessFile.close();
        System.out.println("update sucess");
    }
}
