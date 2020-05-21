package ua.axiom.model.actors;

import ua.axiom.model.Role;
import ua.axiom.persistance.Fabricable;

public class Driver extends User implements Fabricable<Driver> {

    public Driver(long id) {
        super(id);
    }

    @Override
    public Role getRole() {
        return Role.DRIVER;
    }

    @Override
    public Driver fabricate(String[] params) {
        return null;
    }
}
