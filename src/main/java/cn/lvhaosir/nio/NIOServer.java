package cn.lvhaosir.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author lvhaosir 2020/3/11 21:13
 * @Description: TODO()
 * @Version v1.0
 **/
public class NIOServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 得到 Selector
        Selector selector = Selector.open();
        // 绑定一个端口，在服务器上监听
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        serverSocketChannel.configureBlocking(false);
        // 把ServerSocketChannel注册到selector，关心事件为OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 循环等待客户端连接
        while (true) {
            // 等待1s，如果没有时间，返回
            if (selector.select(1000) == 0) {
                System.out.println("服务器等待了1s，无连接");
                continue;
            }
            /**
             * 如果返回的 >0 ，就获取相关的SelectionKey集合
             * 1.如果返回的 >0，表示已经获取到关注的实际
             * 2.selector.selectedKeys() 返回关注事件的集合
             * 通过SelectionKey反向获取通道
             */
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                // 根据key对应的通道发生的事件做相应处理
                if (key.isAcceptable()) {
                    // 如果是OP_ACCEPT，有新的客户端连接
                    // 客户端生成一个SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("客户端连接成功。生成socketChannel: " + socketChannel.hashCode());
                    socketChannel.configureBlocking(false);
                    // 将当前的SocketChannel注册到Selector，关注事件为 OP_READ
                    // 关联一个buffer
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    // 获得到该channel关联的buffer
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    channel.read(buffer);
                    System.out.println("from 客户端： " + new String(buffer.array()));
                }
                // 手动从集合中移出当前的selectionKey
                iterator.remove();
            }
        }
    }
}
