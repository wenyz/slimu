package com.wen.factory;

/**
 * Created by Administrator on 2017/5/14.
 */
public class FruitFactory implements Factory {

    public Plant factory(String plant) {

        if (plant == "apple") {
            return new Apple();
        } else if (plant == "banana") {
            return new Banana();
        }

        return null;
    }
}
