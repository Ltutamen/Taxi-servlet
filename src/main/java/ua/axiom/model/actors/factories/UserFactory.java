package ua.axiom.model.actors.factories;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ua.axiom.model.actors.User;
import ua.axiom.persistance.Fabricable;

public class UserFactory implements Fabricable<User> {
    @Override
    public User fabricate(String[] params) {
        throw new NotImplementedException();
    }
}
