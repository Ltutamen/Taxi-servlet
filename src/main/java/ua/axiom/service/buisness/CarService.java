package ua.axiom.service.buisness;

import ua.axiom.core.Context;
import ua.axiom.model.actors.Car;
import ua.axiom.persistance.repository.impl.CarRepository;

public class CarService {
    private final CarRepository carRepository;

    {
        carRepository = Context.get(CarRepository.class);
    }

    public Car getCarById(long id) {
        return carRepository.findOne(id).iterator().next();
    }
}
