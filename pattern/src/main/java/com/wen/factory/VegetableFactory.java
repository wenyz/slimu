package com.wen.factory;

/**
 * Created by Administrator on 2017/5/14.
 */
public class VegetableFactory implements Factory {
    public Plant factory(String plant) {

        if (plant == "tomato") {
            return new Tomato();
        } else if (plant == "potato") {
            return new Potato();
        }

        return null;
    }
}
