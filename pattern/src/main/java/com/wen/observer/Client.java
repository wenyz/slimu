package com.wen.observer;

/**
 * Created by Administrator on 2017/5/14.
 */
public class Client {

    public static void main(String[] args){


        ConcreateSubject sj = new ConcreateSubject();

        ConcreateObserver observer1 = new ConcreateObserver();
        ConcreateObserver observer2 = new ConcreateObserver();
        ConcreateObserver observer3 = new ConcreateObserver();
        ConcreateObserver observer4 = new ConcreateObserver();

        sj.regist(observer1);
        sj.regist(observer2);
        sj.regist(observer3);
        sj.regist(observer4);

        sj.setState(100);
        sj.notifyObserver();

        System.out.println("=========================");

        sj.setState(300);
        sj.notifyObserver();

    }
}
