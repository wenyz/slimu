package com.wen.proxy.staticproxy;

/**
 * Created by Administrator on 2017/5/14.
 */
public class Client {

    public static void main(String[] args){

        yanchu yanchu = new YanyuanProxy(new Yanyuan());

        yanchu.qianyue();
        yanchu.bujing();
        yanchu.yanchu();
        yanchu.shouqian();

    }
}
