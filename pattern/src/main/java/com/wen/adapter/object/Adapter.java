package com.wen.adapter.object;

import com.wen.adapter.clazz.*;

/**
 * Created by Administrator on 2017/5/3.
 */
public class Adapter implements com.wen.adapter.object.Target {

    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    public void operation1() {
        this.adaptee.operation1();
    }

    public void operation2() {

    }
}
