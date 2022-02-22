package cn.kungreat.one;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class EachServer {

    public static void main(String[] args) throws Exception {
        final EchoServerInboundHandle echoServerInboundHandle = new EchoServerInboundHandle();
        EventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(nioEventLoopGroup).channel(NioServerSocketChannel.class)
                    .localAddress(9999).childHandler(new ChannelInitializer<NioSocketChannel>() {
                    //添加inboundHandle 到子channel 的pipeline 链路  [标记为Sharable 所有客户端都共用一个]
                @Override
                protected void initChannel(NioSocketChannel ch) throws Exception {
                    System.out.println("ServerinitChannel");
                    ch.pipeline().addLast(echoServerInboundHandle);
                }
            });
            //使用的是NIO 就是异步模式绑定服务器、调用sync会阻塞直到绑定完成
            ChannelFuture sync = serverBootstrap.bind().sync();
            sync.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭nioEventLoopGroup 释放所有的资源
            nioEventLoopGroup.shutdownGracefully().sync();
        }
    }
}
