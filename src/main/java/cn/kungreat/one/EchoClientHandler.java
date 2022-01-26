package cn.kungreat.one;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

@ChannelHandler.Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    
    /*  SimpleChannelInboundHandler --> ChannelInboundHandlerAdapter
    * 在客户端、先调用channelRead再回调--> 当channelRead0 方法完成时,说明消息已经接收了、并且已经处理完了
    * SimpleChannelInboundHandler 负责释放资源
    */

    //连接激活的时候发送一条信息
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive");
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello-netty",CharsetUtil.UTF_8));
    }

    //数据可能不是一次性到达、分批发过来
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("Cread0:"+msg.toString(CharsetUtil.UTF_8));
    }

    /*
     * 如果不重写此方法、有异常的时候将传递给下一个链路
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        cause.printStackTrace();
    }
}
