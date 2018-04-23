package com.wen.singleton;

/**
 * Created by Administrator on 2017/5/14.
 */
public class EagerSingleton {

    private static final EagerSingleton m_instance = new EagerSingleton();

    private EagerSingleton() {
    }

    ;

    public static EagerSingleton getInstance() {
        return m_instance;
    }

}
