package com.wen.adapter.object;

/**
 * Created by Administrator on 2017/5/4.
 */
public class Client {

    public static void main(String[] args) {

        Adapter adapter = new Adapter(new Adaptee());

        adapter.operation1();

    }

}
