package ua.axiom.persistance.dao;

import ua.axiom.model.actors.Admin;
import ua.axiom.persistance.jdbcbased.repository.AbstractRepository;

public abstract class AdminDao extends AbstractRepository<Long, Admin> {
    public AdminDao() {
    }
}
