package com.wen.decorator;

/**
 * Created by Administrator on 2017/5/11.
 */
public class CarDecorator implements ICar {

    private ICar car;

    public CarDecorator(ICar superCar) {
        car = superCar;
    }

    public void run() {
        car.run();
    }
}
