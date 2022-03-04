package cn.kungreat.one;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

public class ByteBufTs {
    //定义一个channel组 管理所有的cnannel  GlobalEventExecutor.INSTANCE全局事件执行器是个单例  组有一套自动的管理机制
    private static final ChannelGroup CHANNEL_GROUP = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
//    IdleStateHandler 心逃检查  会把事件IdleStateEvent传给 给下一个handler 会调用UserEventTriggered方法
    private static final IdleStateHandler IDLE_STATE_HANDLER = new IdleStateHandler(3,5
            ,10, TimeUnit.SECONDS);

    public static void main(String[] args) {
        ByteBuf buffer = Unpooled.buffer(16);
        System.out.println(buffer.isReadable());//writerIndex > readerIndex;
        System.out.println(buffer.isReadable(0));//writerIndex - readerIndex >= 0;
        System.out.println(buffer.capacity());//16 返回此缓冲区可以包含的字节数
        //writerIndex 增加 会自动扩容
        for(int x=0;x<12;x++){
            buffer.writeByte(15);
        }
        //writerIndex 指针位置
        System.out.println(buffer.writerIndex());//12
        //设置 writerIndex 指针位置
        buffer.writerIndex(3);
        System.out.println(buffer.writableBytes()); //capacity() - writerIndex;
        System.out.println(buffer.maxWritableBytes());//maxCapacity() - writerIndex;
        //扩容
//        buffer.capacity(32);
        //readerIndex  没有增加
        for(int x=0;x<12;x++){
            System.out.println(buffer.getByte(x));
        }
        //可以读取的数量
        System.out.println(buffer.readableBytes());//12
        //当前readerIndex 指针位置
        System.out.println(buffer.readerIndex());
        //设置当前指针
        buffer.readerIndex(0);
        //readerIndex  有增加
        for(int x=0;x<10;x++){
            System.out.println(buffer.readByte());
        }

        Unpooled.copiedBuffer("dfsdfsdfsdf", Charset.forName("UTF-8"));
    }
}
