package ua.axiom.model.actors;

import ua.axiom.model.Role;

import javax.persistence.Entity;

@Entity
public class Admin extends User {
    public Admin(long id) {
        super(id);
    }

    @Override
    public Role getRole() {
        return Role.ADMIN;
    }
}
