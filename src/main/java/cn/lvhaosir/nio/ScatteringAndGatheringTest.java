package cn.lvhaosir.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * Scattering: 将数据写入buffer时，可以采用buffer数组，依次写入【分散】
 * Gathering：从buffer读取数据时，可以采用buffer数组，依次读
 **/
public class ScatteringAndGatheringTest {

    public static void main(String[] args) throws Exception {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);
        serverSocketChannel.socket().bind(inetSocketAddress);

        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        // 等待客户端连接（telnet）
        SocketChannel socketChannel = serverSocketChannel.accept();

        // 假定从客户端接收8个字节
        int maxMessageLength = 8;

        while (true) {

            int byteRead = 0;

            while (byteRead < maxMessageLength ) {
                long read = socketChannel.read(byteBuffers);
                byteRead += read;
                System.out.println("byteRead = "+byteRead);
                Arrays.asList(byteBuffers).stream()
                        .map(buffer -> "postion = "+buffer.position() +", limit = "+ buffer.limit()).forEach(System.out::println);

            }

            // 将所有的buffer全部反转
            Arrays.asList(byteBuffers).forEach(buffer -> buffer.flip());

            // 将数据读出，显示到客户端
            long byteWirte = 0;
            while (byteWirte < maxMessageLength) {
                long write = socketChannel.write(byteBuffers);
                byteWirte += write;
            }

            // 将所有buffer进行clear操作
            Arrays.asList(byteBuffers).forEach(buffer -> buffer.clear());
            System.out.println("byteRead= "+byteRead +" byteWirte= "+byteWirte);
        }
    }
}
