package zy.netty.sixthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestServerHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person msg) throws Exception {
        // 服务端接收到了 proto buffer 处理的对象
        MyDataInfo.Person person = msg;
        System.out.println(person.getName());
        System.out.println(person.getAge());
        System.out.println(person.getAddress());
    }

}
