package com.wenyize.nettyloop.sixthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        int randomInt = new Random().nextInt(3);

        MyDataInfo.MyMessage myMessage = null;

        if (0 == randomInt){
            myMessage = MyDataInfo.MyMessage.newBuilder()

                    .setDataType(MyDataInfo.MyMessage.DataType.PersonType)
                    .setPerson(MyDataInfo.Person.newBuilder().setName("aab")
                            .setAge(12).setAddress("海南").build()).build();

        }else if (1 ==randomInt){
            myMessage = MyDataInfo.MyMessage.newBuilder()

                    .setDataType(MyDataInfo.MyMessage.DataType.DogType)
                    .setDog(MyDataInfo.Dog.newBuilder().setName("狗")
                            .setAge(12).build()).build();
        }else if (2== randomInt){
            myMessage = MyDataInfo.MyMessage.newBuilder()

                    .setDataType(MyDataInfo.MyMessage.DataType.CatType)
                    .setCat(MyDataInfo.Cat.newBuilder().setName("cat").setCity("上海")
                            .build()).build();
        }

        ctx.channel().writeAndFlush(myMessage);
    }
}
