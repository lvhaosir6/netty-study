package cn.lvhaosir.nio;

import java.nio.ByteBuffer;

/**
 * @author lvhaosir 2020/2/26 20:52
 * @Description: TODO()
 * @Version v1.0
 **/
public class ReadOnlyBuffer {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);

        for (int i = 0; i < buffer.capacity() ; i++) {
            buffer.put((byte)i);
        }

        buffer.flip();

        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        System.out.println(readOnlyBuffer.get());

        while (readOnlyBuffer.hasRemaining()) {
            System.out.println(readOnlyBuffer.get());
        }
        readOnlyBuffer.put((byte)1);
    }
}
