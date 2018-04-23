package com.wen.factory;

/**
 * Created by Administrator on 2017/5/14.
 */
public class Client {

    public static void main(String[] args) {

        Factory fruitFactory = new FruitFactory();

        Apple apple = (Apple)fruitFactory.factory("apple");
        Plant banana = fruitFactory.factory("banana");


        apple.sayHello();
        apple.sayGoodBye();
        banana.sayHello();

        Factory vegetableFactory = new VegetableFactory();
        Plant tomato = vegetableFactory.factory("tomato");
        Plant potato = vegetableFactory.factory("potato");

        tomato.sayHello();
        potato.sayHello();

    }
}
