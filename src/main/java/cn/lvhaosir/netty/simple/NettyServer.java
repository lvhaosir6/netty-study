package cn.lvhaosir.netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

    public static void main(String[] args) throws Exception {

        // 创建BossGroup 和 WorkerGroup
        EventLoopGroup bossGroup = new NioEventLoopGroup();

        EventLoopGroup workerGroup = new NioEventLoopGroup();


        // 创建服务器端的启动对象，配置参数
        ServerBootstrap bootstrap = new ServerBootstrap();


        bootstrap.group(bossGroup, workerGroup) // 设置两个线程组
                .channel(NioServerSocketChannel.class)    // 使用 NioSocketChannel
                .option(ChannelOption.SO_BACKLOG, 128) // 设置线程队列等待连接个数
                .childOption(ChannelOption.SO_KEEPALIVE, true)  // 设置保持活动连接状态
                .childHandler(new ChannelInitializer<SocketChannel>() {  // 给WorkerGroup的EventLoop对应的管道设置处理器
                    // 给 Pipeline 设置处理器
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(null);
                    }
                });

        System.out.println("服务器 is ready ...");

        // 绑定端口并且同步（启动服务器），生成一个 ChannelFuture 对象
        ChannelFuture cf = bootstrap.bind(6688).sync();

        // 对关闭通道进行监听
        cf.channel().closeFuture().sync();


    }

}
