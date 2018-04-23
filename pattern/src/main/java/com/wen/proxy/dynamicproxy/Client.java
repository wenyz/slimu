package com.wen.proxy.dynamicproxy;

import com.sun.org.apache.bcel.internal.util.ClassLoader;

import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/5/14.
 */
public class Client {

    public static void main(String[] args){

        Yanyuan yanyuan = new Yanyuan();
        YanchuHandler yanchuHandler = new YanchuHandler(yanyuan);

        yanchu daili =  (yanchu) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),new Class[] {yanchu.class},yanchuHandler);

        daili.qianyue();
        daili.bujing();
        daili.yanchu();
        daili.shouqian();


    }
}
