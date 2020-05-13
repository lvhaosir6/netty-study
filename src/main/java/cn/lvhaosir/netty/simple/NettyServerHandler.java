package cn.lvhaosir.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 自定义Handler需要继承Netty规定好的某个HandlerAdapter（规范）
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取数据的事件（可以读取到客户端发送的消息）
     *
     * @param ctx 上下文对象；
     *            含有 1.管道Piepeline；2.通道Channel；3.地址
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Server ctx = " + ctx);
        // 将msg转成ByteBuf （ByteBuf为Netty中的，不是NIO中的ByteBuffer）
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("客户端发送的消息是：" + buf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址：" + ctx.channel().remoteAddress());
    }

    /**
     * 数据读取完毕
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 将数据写到缓存，并刷新，并且对于这个数据需要进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello,客户端", CharsetUtil.UTF_8));
    }

    /**
     * 处理异常，一般是关闭通道
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
