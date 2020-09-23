package ua.axiom.persistance.jdbcbased.repository.impl;

import ua.axiom.core.annotations.Autowired;
import ua.axiom.core.annotations.InitMethod;
import ua.axiom.model.actors.User;
import ua.axiom.persistance.jdbcbased.repository.MultiTableRepository;


public class UsersRepository extends MultiTableRepository<Long, User> {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private DriverRepository driverRepository;

    @InitMethod
    private void init() {
        super.addRepository(clientRepository);
        super.addRepository(adminRepository);
        super.addRepository(driverRepository);
    }
}
