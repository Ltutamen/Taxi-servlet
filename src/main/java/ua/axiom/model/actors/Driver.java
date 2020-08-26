package ua.axiom.model.actors;

import ua.axiom.model.Role;

import java.math.BigDecimal;

public class Driver extends User {

    private long car_id;

    private BigDecimal balance;

    private Long current_order_id;

    public long getCarId() {
        return car_id;
    }

    public void setCarId(long carId) {
        this.car_id = carId;
    }

    public long getCurrentOrderId() {
        return current_order_id;
    }

    public void setCurrentOrderId(Long currentOrderId) {
        this.current_order_id = currentOrderId;
    }

    public BigDecimal getMoney() {
        return balance;
    }

    public void setMoney(BigDecimal money) {
        this.balance = money;
    }

    public Driver(long id) {
        super(id);
    }

    @Override
    public Role getRole() {
        return Role.DRIVER;
    }

}
