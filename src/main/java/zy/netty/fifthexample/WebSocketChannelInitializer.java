package zy.netty.fifthexample;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebSocketChannelInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new HttpServerCodec()); // WebSocket 是基于HTTP的，所以要加入HTTP的编解码器
        pipeline.addLast(new ChunkedWriteHandler()); // 以块的方式来写的处理器
        // HttpObjectAggregator是很重要的处理器，用的很多，Netty将消息分段发送，
        // HttpObjectAggregator可以将分段聚合到一起形成完整的请求或响应消息
        pipeline.addLast(new HttpObjectAggregator(8192));
        // 这个处理器会完成运行websocket服务的繁重工作，负责websocket握手、关闭、ping/pong等工作
        // 文本、二进制数据帧由后续的 Handler 处理。
        // WebSocket的协议名 是 ws，如 ws://server:port/context_path。而参数中的 /ws 指的是 context_path
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        pipeline.addLast(new TextWebSocketFrameHandler()); // 自定义处理器
    }
}
