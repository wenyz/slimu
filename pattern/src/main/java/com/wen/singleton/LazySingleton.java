package com.wen.singleton;

/**
 * Created by Administrator on 2017/5/14.
 */
public class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton() {
    }

    ;

    public static synchronized LazySingleton getInstance() {

        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
