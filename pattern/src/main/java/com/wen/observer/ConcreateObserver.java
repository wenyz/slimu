package com.wen.observer;

/**
 * Created by Administrator on 2017/5/14.
 */
public class ConcreateObserver implements Observer {

    public void update(int state) {
        System.out.println("the value is :"+state);
    }
}
