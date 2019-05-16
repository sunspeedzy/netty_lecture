package zy.netty.sixthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 客户端构造 proto buf中的对象
        MyDataInfo.Person person = MyDataInfo.Person.newBuilder()
                .setName("张三").setAge(20).setAddress("北京").build();
        // 将构造好的对象发送给服务端
        ctx.channel().writeAndFlush(person);
    }
}
