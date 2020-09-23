package ua.axiom.model.actors.factories;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ua.axiom.model.actors.User;
import ua.axiom.persistance.jdbcbased.Fabric;

public class UserFactory implements Fabric<User> {
    @Override
    public User fabricate(String[] params) {
        throw new NotImplementedException();
    }
}
