package com.wen.iterator;

/**
 * Created by Administrator on 2017/5/14.
 */
public interface Iterator {

    public void first();
    public void next();

    public boolean hasNext();
    public boolean isFirst();
    public boolean isLast();

    public Object getCurrentIterator();



}
