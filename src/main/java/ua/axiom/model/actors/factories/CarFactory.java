package ua.axiom.model.actors.factories;

import ua.axiom.model.actors.Car;
import ua.axiom.persistance.Fabricable;

public class CarFactory implements Fabricable<Car> {
    @Override
    public Car fabricate(String[] params) {
        Car car = new Car(Long.parseLong(params[1]));
        car.setaClass(Car.Class.values()[Integer.parseInt(params[2])]);
        car.setModelName(params[3]);

        return car;
    }
}
