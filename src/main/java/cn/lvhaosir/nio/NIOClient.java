package cn.lvhaosir.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author lvhaosir 2020/3/11 21:42
 * @Description: TODO()
 * @Version v1.0
 **/
public class NIOClient {

    public static void main(String[] args) throws IOException {

        // 得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6667);

        if (!socketChannel.connect(inetSocketAddress)) {
            while (!socketChannel.finishConnect()) {
                System.out.println("因为连接需要时间，客户端不会阻塞，可做其他工作");
            }
        }

        String str = "Hello,NIO!";
        ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes());

        // 发送数据，将buffer数据写入channel
        socketChannel.write(byteBuffer);
        System.in.read();
    }

}
