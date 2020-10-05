package ua.axiom.persistance.jdbcbased.repository.impl;

import ua.axiom.core.annotations.Autowired;
import ua.axiom.core.annotations.InitMethod;
import ua.axiom.model.actors.User;
import ua.axiom.persistance.dao.AdminDao;
import ua.axiom.persistance.dao.ClientDao;
import ua.axiom.persistance.dao.DriverDao;
import ua.axiom.persistance.jdbcbased.repository.MultiTableRepository;


public class UsersRepositoryJDBC extends MultiTableRepository<Long, User> {
    @Autowired
    private ClientDao clientRepository;

    @Autowired
    private AdminDao adminRepository;

    @Autowired
    private DriverDao driverRepository;

    @InitMethod
    private void init() {
        super.addRepository(clientRepository);
        super.addRepository(adminRepository);
        super.addRepository(driverRepository);
    }
}