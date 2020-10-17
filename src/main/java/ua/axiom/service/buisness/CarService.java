package ua.axiom.service.buisness;

import ua.axiom.core.annotations.Autowired;
import ua.axiom.model.actors.Car;
import ua.axiom.persistance.ormbased.repository.impl.CarRepositoryORM;

public class CarService {
    @Autowired
    private CarRepositoryORM carRepository;

    public Car getCarById(long id) {
        return carRepository.read(id);
    }
}
