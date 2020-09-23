package ua.axiom.model.actors.factories;

import ua.axiom.core.annotations.Component;
import ua.axiom.model.UserLocale;
import ua.axiom.model.actors.Driver;
import ua.axiom.persistance.jdbcbased.Fabric;

import java.math.BigDecimal;

@Component
public class DriverFactory implements Fabric<Driver> {
    @Override
    public Driver fabricate(String[] params) {
        Driver driver = new Driver(Long.parseLong(params[1]));

        driver.setBanned(params[2].equals("1"));
        driver.setLocale(UserLocale.valueOf(params[3]));
        driver.setPassword(params[4]);
        driver.setUsername(params[5]);
        driver.setMoney(new BigDecimal(params[6]));
        driver.setCarId(Long.parseLong(params[7]));
        driver.setCurrentOrderId(params[8] == null ? null : Long.parseLong(params[8]));

        return driver;
    }
}
