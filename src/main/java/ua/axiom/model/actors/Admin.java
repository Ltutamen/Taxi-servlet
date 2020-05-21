package ua.axiom.model.actors;

import ua.axiom.model.Role;

public class Admin extends User {
    public Admin(long id) {
        super(id);
    }

    @Override
    public Role getRole() {
        return Role.ADMIN;
    }
}
