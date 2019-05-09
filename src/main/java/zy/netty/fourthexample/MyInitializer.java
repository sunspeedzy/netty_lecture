package zy.netty.fourthexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class MyInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // ChannelPipeline 的 addLast 方法，使用了 责任链的设计模式，
        // 即 请求由一个个handler去处理，再原路返回
        pipeline.addLast(
                new IdleStateHandler(5,
                        7,3, TimeUnit.SECONDS)
        );
        pipeline.addLast(new MyServerHandler());
    }
}
