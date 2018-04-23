package com.wen.iterator;

/**
 * Created by Administrator on 2017/5/14.
 */
public interface Aggregate<E> {

    public void addElement(E e);
    public void removeElement(E e);

    public Iterator iterator();
}
