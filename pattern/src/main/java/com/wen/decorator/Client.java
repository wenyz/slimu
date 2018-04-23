package com.wen.decorator;

/**
 * Created by Administrator on 2017/5/11.
 */
public class Client {

    public static void main(String[] args) {

        System.out.println("car is stand by=============");
        ICar car = new Car();
        car.run();
        System.out.println("waterCar is stand by=============");
        ICar waterCar = new WaterCar(car);
        waterCar.run();
        System.out.println("waterCar2 is stand by=============");
        ICar waterCar2 = new WaterCar(new CarDecorator(car));
        waterCar2.run();
        System.out.println("waterCar3 is stand by=============");
        WaterCar waterCar3 = new WaterCar(new CarDecorator(car));
        waterCar3.run();
        waterCar3.swim();
        System.out.println("super car is stand by=============");
        ICar superCar = new AICar(new FlyCar(new WaterCar(new CarDecorator(car))));
        superCar.run();
    }
}
