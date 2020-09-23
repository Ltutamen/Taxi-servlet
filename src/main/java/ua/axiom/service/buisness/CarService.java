package ua.axiom.service.buisness;

import ua.axiom.core.annotations.Autowired;
import ua.axiom.model.actors.Car;
import ua.axiom.persistance.jdbcbased.repository.impl.CarRepository;

public class CarService {
    @Autowired
    private CarRepository carRepository;


    public Car getCarById(long id) {
        return carRepository.findOne(id).iterator().next();
    }
}
