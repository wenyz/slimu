package com.wen.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/14.
 */
public class ConcreateSubject implements Subject {


    private  List<Observer> list = new ArrayList<Observer>();
    private int state;


    public void regist(Observer observer) {
        list.add(observer);
    }

    public void unRegist(Observer observer) {

        list.remove(observer);
    }

    public void notifyObserver() {

        for (int i=0;i<list.size();i++){

            list.get(i).update(state);
        }

    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
