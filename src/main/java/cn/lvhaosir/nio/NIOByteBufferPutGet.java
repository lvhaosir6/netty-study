package cn.lvhaosir.nio;

import java.nio.ByteBuffer;

/**
 * @author lvhaosir 2020/2/26 20:48
 * @Description: TODO()
 * @Version v1.0
 **/
public class NIOByteBufferPutGet {

    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(512);
        buffer.putInt(1);
        buffer.putLong(1L);
        buffer.putChar('a');
        buffer.putShort((short)1);

        buffer.flip();

        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getShort());
    }

}
