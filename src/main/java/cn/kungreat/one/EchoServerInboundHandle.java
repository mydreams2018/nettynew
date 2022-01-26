package cn.kungreat.one;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

@ChannelHandler.Sharable //标记可以被多个channel安全地共享
public class EchoServerInboundHandle extends ChannelInboundHandlerAdapter {

    /*
    * ChannelInboundHandlerAdapter 当channelRead 方法完成时
    * 我们仍然需要把消息回送给客户端、而write是异步的、
    * 当channelReadComplete 完成时消息才发送完成  writeAndFlush 发送并释放资源
    */


    /*
     * 数据可能不是一次性到达、分批发过来
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("Sread:"+byteBuf.toString(CharsetUtil.UTF_8));
        //写入数据
        ctx.write(msg);
    }

    /*未决消息
    * 是指目前暂存在channelOutboundBuffer 中的消息
    * 在下一次调用flush 或者 writeAndFlush 方法时将会尝试写出到套接字
    */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("SchannelReadComplete");
        //将未决消息写出 并且刷新 然后关闭流
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    /*
    * 如果不重写此方法、有异常的时候将传递给下一个链路
    */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
