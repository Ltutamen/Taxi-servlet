package ua.axiom.service.buisness;

import ua.axiom.core.annotations.Autowired;
import ua.axiom.model.actors.Car;
import ua.axiom.persistance.jdbcbased.repository.impl.CarRepositoryJDBC;

public class CarService {
    @Autowired
    private CarRepositoryJDBC carRepository;


    public Car getCarById(long id) {
        return carRepository.read(id);
    }
}
