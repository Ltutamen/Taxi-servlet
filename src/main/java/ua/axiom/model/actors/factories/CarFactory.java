package ua.axiom.model.actors.factories;

import ua.axiom.model.actors.Car;
import ua.axiom.persistance.Fabricable;
import ua.axiom.persistance.misc.representation.GeneralPersisting;

public class CarFactory implements Fabricable<Car> {
    @Override
    public Car fabricate(String[] params) {
        Car car = new Car(Long.parseLong(params[1]));
        try {
            car.setaClass((Car.Class)GeneralPersisting.getObject(Car.class.getDeclaredField("aClass"), params[2]));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e.getMessage());
        }
        car.setaClass(Car.Class.values()[Integer.parseInt(params[2])]);
        car.setModelName(params[3]);

        return car;
    }
}
