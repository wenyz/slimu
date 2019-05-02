package com.wenyize.nettyloop.sixthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {

        switch (msg.getDataType()) {
            case PersonType:
                System.out.println(msg.getPerson().getName());
                System.out.println(msg.getPerson().getAge());
                System.out.println(msg.getPerson().getAddress());
                break;
            case DogType:
                System.out.println(msg.getDog().getName());
                System.out.println(msg.getDog().getAge());
                break;
            case CatType:
                System.out.println(msg.getCat().getName());
                System.out.println(msg.getCat().getCity());
                break;
        }
    }
}
