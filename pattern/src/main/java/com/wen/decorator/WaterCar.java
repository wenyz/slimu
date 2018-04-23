package com.wen.decorator;

/**
 * Created by Administrator on 2017/5/11.
 */
public class WaterCar extends CarDecorator {

    public WaterCar(ICar superCar) {
        super(superCar);
    }

    public void swim() {
        System.out.println("watercar is swimming");
    }

    @Override
    public void run() {
        super.run();
        System.out.println("watercar is swim in the water");
        swim();
    }
}
