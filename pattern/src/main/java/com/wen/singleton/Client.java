package com.wen.singleton;

/**
 * Created by Administrator on 2017/5/14.
 */
public class Client {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        EagerSingleton eagerSingleton = EagerSingleton.getInstance();

        System.out.print(eagerSingleton.getClass().getName());

        LazySingleton lazySingleton = LazySingleton.getInstance();

        System.out.print(lazySingleton.getClass().getName());

    }
}
