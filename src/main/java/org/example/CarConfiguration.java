package org.example;

import org.example.builder.Car;

public class CarConfiguration {

    public static void main(String[] args) {
        Car car1 = new Car.Builder()
                .setEngine("V8")
                .setTransmission("Automatic")
                .setInterior("Leather")
                .setColor("Red")
                .setSunroof(true)
                .setGPS(true)
                .setSafetyPackage(true)
                .build();

        Car car2 = new Car.Builder()
                .setEngine("V6")
                .setTransmission("Manual")
                .setColor("Blue")
                .build();

        System.out.println(car1);
        System.out.println(car2);
    }
}
