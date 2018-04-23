package com.wen.strategy;

/**
 * Created by Administrator on 2017/5/14.
 */
public class Context {

    ProtocalStrategy protocalStrategy;

    public Context(ProtocalStrategy protocalStrategy){
        this.protocalStrategy = protocalStrategy;
    }

    public void process(){

        protocalStrategy.process();
    }
}
