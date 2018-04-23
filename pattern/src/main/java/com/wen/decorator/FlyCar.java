package com.wen.decorator;

/**
 * Created by Administrator on 2017/5/11.
 */
public class FlyCar extends CarDecorator {

    public FlyCar(ICar superCar) {
        super(superCar);
    }

    public void fly() {
        System.out.println("flycar is flying");
    }

    @Override
    public void run() {
        super.run();
        System.out.println("flycar is flying in the sky");
        fly();
    }
}
