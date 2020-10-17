package ua.axiom.model.actors;

import ua.axiom.model.Role;

import javax.persistence.Entity;

@Entity
public class Admin extends User {

    public Admin() {
    }

    @Override
    public Role getRole() {
        return Role.ADMIN;
    }
}
