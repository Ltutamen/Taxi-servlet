package ua.axiom.model.actors.factories;

import ua.axiom.model.UserLocale;
import ua.axiom.model.actors.Admin;
import ua.axiom.persistance.Fabricable;

public class AdminFactory implements Fabricable<Admin> {
    @Override
    public Admin fabricate(String[] params) {
        long id = Long.parseLong(params[1]);
        Admin admin = new Admin(id);

        admin.setBanned(Boolean.parseBoolean(params[2]));
        //  todo userlocale throw new dePersister
        admin.setLocale(UserLocale.valueOf(params[3]));
        admin.setPassword(params[4]);
        admin.setUsername(params[5]);

        return admin;
    }
}
