package com.wen.observer;

/**
 * Created by Administrator on 2017/5/14.
 */
public interface Subject {

    public void regist(Observer observer);
    public void unRegist(Observer observer);
    public void notifyObserver();
}
