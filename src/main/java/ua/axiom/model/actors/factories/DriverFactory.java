package ua.axiom.model.actors.factories;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ua.axiom.model.actors.Driver;
import ua.axiom.persistance.Fabricable;

public class DriverFactory implements Fabricable<Driver> {
    @Override
    public Driver fabricate(String[] params) {
        throw new NotImplementedException();
    }
}
