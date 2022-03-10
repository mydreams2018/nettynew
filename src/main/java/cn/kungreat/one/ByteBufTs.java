package cn.kungreat.one;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
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

        System.out.println("arrayOffset"+buffer.arrayOffset()); //0
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
        for(int x=0;x<buffer.readableBytes();x++){
            System.out.println(buffer.readByte());
        }

        System.out.println("-------------------------");
        Unpooled.buffer(128);
        Unpooled.directBuffer(128);
        ByteBuf wrappedBuffer = Unpooled.wrappedBuffer(new byte[128], new byte[256]);
        ByteBuf copiedBuffer  = Unpooled.copiedBuffer(ByteBuffer.allocate(128));
        ByteBuf byteBuf1 = Unpooled.copiedBuffer("dfsdfsdfsdf", Charset.forName("UTF-8"));
        ByteBuf byteBuf2 = Unpooled.copiedBuffer("dfsdfsdfsdf", Charset.forName("UTF-8"));
        //CompositeByteBuf 可以把需要合并的bytebuf 组合起来，对外提供统一的readindex和writerindex

        //将多个缓冲区显示为单个合并缓冲区的虚拟缓冲区。  添加几个数量后合并成一个新的数组 默认16
        CompositeByteBuf compositeBuffer = Unpooled.compositeBuffer();
        compositeBuffer.addComponent(copiedBuffer);
        //true 增加写入指针位置  不然写入指针不动
        compositeBuffer.addComponent(true,byteBuf1);
        //从指定位置插入 不会删除旧元素 位置会顺移
        compositeBuffer.addComponent(true,byteBuf2);

        //合并后的总数量
        System.out.println(compositeBuffer.capacity());
        //删除 components 数组的第N个索引   byteBufs.removeComponent(1);
        System.out.println(compositeBuffer.capacity());
        for(ByteBuf buf:compositeBuffer){
            System.out.println(buf);
        }
        //可以读的总字节
        System.out.println(compositeBuffer.readableBytes());
        System.out.println(compositeBuffer.readerIndex());
        byte[] bt = new byte[compositeBuffer.readableBytes()];
        //有风险, 如果添加数据的顺序有问题 或者 添加了空的数据 就会写入空的到bt
        CompositeByteBuf bytes = compositeBuffer.getBytes(compositeBuffer.readerIndex(), bt);
        System.out.println(Arrays.toString(bt));
    }
}
