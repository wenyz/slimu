package com.wen.iterator;

/**
 * Created by Administrator on 2017/5/14.
 */
public class Client {

    public static void main(String[] args){

        Aggregate<String> list = new ConcreteAggregate<String>();

        list.addElement("aaa");
        list.addElement("bbb");
        list.addElement("ccc");
        list.addElement("ddd");

        Iterator iterator = list.iterator();

        while (iterator.hasNext()){

            System.out.println(iterator.getCurrentIterator());
            iterator.next();
        }


    }
}
