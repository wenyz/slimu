package com.wen.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/5/14.
 */
public class YanchuHandler implements InvocationHandler {

    yanchu yanchu;

    public YanchuHandler(yanchu yanchu) {
        super();
        this.yanchu = yanchu;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (method.getName().equals("yanchu")) {
            method.invoke(this.yanchu, args);
        } else {
            System.out.println("jiaogei daili zuo");
        }
        return null;
    }
}
