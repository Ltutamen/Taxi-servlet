package ua.axiom.model.actors;

import ua.axiom.repository.Fabricable;

public class Driver extends User implements Fabricable<Driver> {

    private Driver(long id) {
        super(id);
    }

    @Override
    public Driver fabricate(String[] params) {
        return null;
    }
}
