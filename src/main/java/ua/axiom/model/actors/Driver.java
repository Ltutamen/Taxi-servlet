package ua.axiom.model.actors;

import ua.axiom.model.Role;

import java.math.BigDecimal;

public class Driver extends User {

    private long carId;

    private BigDecimal money;

    private Long currentOrderId;

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public long getCurrentOrderId() {
        return currentOrderId;
    }

    public void setCurrentOrderId(Long currentOrderId) {
        this.currentOrderId = currentOrderId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Driver(long id) {
        super(id);
    }

    @Override
    public Role getRole() {
        return Role.DRIVER;
    }

}
