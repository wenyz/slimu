package com.wen.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/14.
 */
public class ConcreteAggregate<E> implements Aggregate<E> {

    private List<E> list = new ArrayList<E>();

    private int cursor;

    private Iterator iterator = new MyIterator();


    public void addElement(E element) {
        list.add(element);
    }

    public void removeElement(E element) {
        list.remove(element);
    }

    public Iterator iterator(){
        return iterator;
    }

    private class MyIterator implements Iterator {

        public void first() {
            cursor = 0;
        }

        public void next() {
            cursor++;
        }

        public boolean hasNext() {

            return list.size() > cursor ? true : false;

        }

        public boolean isFirst() {
            return cursor == 0 ? true : false;
        }

        public boolean isLast() {
            return list.size()-1 == cursor ? true : false;
        }

        public Object getCurrentIterator() {
            return list.get(cursor);
        }
    }

}
