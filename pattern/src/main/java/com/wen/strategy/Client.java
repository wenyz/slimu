package com.wen.strategy;

/**
 * Created by Administrator on 2017/5/14.
 */
public class Client {

    public static void main(String[] args){

        System.out.println("AAAAAAAAAAA is comming");
        Context protocalStrategyA = new Context(new ProtocalAStrategy());

        protocalStrategyA.process();

        System.out.println("BBBBBBBBBBB is comming");
        Context protocalStrategyB = new Context(new ProtocalBStrategy());

        protocalStrategyB.process();

        System.out.println("CCCCCCCCCCC is comming");
        Context protocalStrategyC = new Context(new ProtocalCStrategy());

        protocalStrategyC.process();


    }
}
