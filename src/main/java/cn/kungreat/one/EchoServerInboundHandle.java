package cn.kungreat.one;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

@ChannelHandler.Sharable //标记可以被多个channel安全地共享
public class EchoServerInboundHandle extends ChannelInboundHandlerAdapter {

    private String message;
    /*
    * ChannelInboundHandlerAdapter 当channelRead 方法完成时
    * 我们仍然需要把消息回送给客户端、而write是异步的、
    * 当channelReadComplete 完成时消息才发送完成  writeAndFlush 发送并释放资源
    */


    /*
     *数据可能不是一次性到达、分批发过来
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//          把时间长的任务可以添加到当前的 异步队列中执行 没有先后顺序要求的话 还是一个单线程的 由当前线程执行完后再执行队列中任务
        ctx.channel().eventLoop().execute(()-> System.out.println("execute"));
//        ctx.channel().eventLoop().schedule();

        Thread.sleep(20000);
        ByteBuf byteBuf = (ByteBuf) msg;
        this.message=byteBuf.toString(CharsetUtil.UTF_8);
        System.out.println("Sread:"+this.message);
    }

    /*
    * 在下一次调用flush 或者 writeAndFlush
    */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("SchannelReadComplete");
        ctx.writeAndFlush(Unpooled.copiedBuffer(this.message.getBytes("UTF-8")));
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
