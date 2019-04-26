package zy.netty.firstexample;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    // channelRead0表示 服务器端读到了客户端的请求，可以处理请求，发出响应
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        // 第6课追加
        System.out.println(msg.getClass()); // 查看msg的类
        System.out.println(ctx.channel().remoteAddress()); // 查看和Channel连接的远程地址，如 /0:0:0:0:0:0:0:1:61044
        Thread.sleep(15000);
        ////////////////////////////////////////
        if (msg instanceof HttpRequest){
            // 第6课追加
//            System.out.println("请求方法名：");
            // 不响应对 /favicon.ico 的请求
            HttpRequest httpRequest = (HttpRequest)msg;
            System.out.println("请求方法名：" + httpRequest.method().name());
            URI uri = new URI(httpRequest.uri());
            if ("/favicon.ico".equals(uri.getPath())){
                System.out.println("请求 favicon.ico");
                return;
            }
            /////////////////////////////////////////
            ByteBuf content = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            ctx.writeAndFlush(response);
            // 第6课追加     关闭 Channel
            ctx.channel().close();
            ////////////////////////
        }
    }

    // 第6课追加
    // 重新实现 io.netty.channel.ChannelInboundHandlerAdapter 中的回调方法
    // 通道活动状态时调用的方法
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel active ");
        super.channelActive(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel registered ");
        super.channelRegistered(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Handler added");
        super.handlerAdded(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel inactive ");
        super.channelInactive(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel unregistered ");
        super.channelUnregistered(ctx);
    }
    /////////////////////////////////////////////////////////////////////////////////
}
