package cn.kungreat.one;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.NettyRuntime;
import io.netty.util.internal.SystemPropertyUtil;

public class EachServer {

    public static void main(String[] args) throws Exception {
        System.out.println(Math.max(1, SystemPropertyUtil.getInt(
                "io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2)));
        // 默认创建当前线程数*2 的线程
        EventLoopGroup bossGroup = new NioEventLoopGroup(2);
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workGroup).channel(NioServerSocketChannel.class)
                    //队列
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .localAddress(9999).childHandler(new ChannelInitializer<NioSocketChannel>() {
                    //添加inboundHandle 到子channel 的pipeline 链路  [标记为Sharable 所有客户端都共用一个]
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            System.out.println("ServerinitChannel");
                            ch.pipeline().addLast(new EchoServerInboundHandle());
                        }
            });
            //使用的是NIO 就是异步模式绑定服务器、调用sync会阻塞直到绑定完成
            ChannelFuture cf = serverBootstrap.bind().sync();
            System.out.println("bind-end");
            //等待通道关闭事件 sync
            cf.channel().closeFuture().sync();
            System.out.println("server-end");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭nioEventLoopGroup 释放所有的资源
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
