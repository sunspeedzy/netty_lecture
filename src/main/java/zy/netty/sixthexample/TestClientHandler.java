package zy.netty.sixthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 随机生成 0、1、2
        int randomInt = new Random().nextInt(3);
        MyDataInfo.MyMessage myMessage = null;
        // 如果
        if ( 0==randomInt ){
            myMessage = MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.PersonType)
                    .setPerson(MyDataInfo.Person.newBuilder()
                            .setName("张三").setAge(20).setAddress("北京").build())
                    .build();
        } else if ( 1==randomInt ) {
            myMessage = MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.DogType)
                    .setDog(MyDataInfo.Dog.newBuilder()
                            .setName("张三的狗").setAge(2).build())
                    .build();
        } else {
            myMessage = MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.CatType)
                    .setCat(MyDataInfo.Cat.newBuilder()
                            .setName("张三的猫").setCity("北京").build())
                    .build();
        }

        ctx.channel().writeAndFlush(myMessage);

        // 客户端构造 proto buf中的对象
//        MyDataInfo.Person person = MyDataInfo.Person.newBuilder()
//                .setName("张三").setAge(20).setAddress("北京").build();
        // 将构造好的对象发送给服务端
//        ctx.channel().writeAndFlush(person);
    }
}
