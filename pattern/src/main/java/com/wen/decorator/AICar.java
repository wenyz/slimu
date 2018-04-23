package com.wen.decorator;

/**
 * Created by Administrator on 2017/5/11.
 */
public class AICar extends CarDecorator {
    public AICar(ICar superCar) {
        super(superCar);
    }

    public void autoDriving() {
        System.out.println("AIcar can auto driving");
    }

    @Override
    public void run() {
        super.run();
        System.out.println("AIcar is auto dring on the ground");
        autoDriving();
    }
}
