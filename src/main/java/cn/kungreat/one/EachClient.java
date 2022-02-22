package cn.kungreat.one;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class EachClient {

    public static void main(String[] args) throws Exception {
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(nioEventLoopGroup).channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(InetAddress.getLocalHost(),9999))
                    .localAddress(new InetSocketAddress(InetAddress.getLocalHost(),8888))
                    .handler(new ChannelInitializer<SocketChannel>() {
                    //添加inboundHandle 到子channel 的pipeline 链路
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new EchoClientHandler());
                }
            });
            //使用的是NIO 就是异步模式连接服务器、调用sync会阻塞直到绑定完成
            ChannelFuture sync = bootstrap.connect().sync();
            sync.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭nioEventLoopGroup 释放所有的资源
            nioEventLoopGroup.shutdownGracefully().sync();
        }
    }
}
