package ua.axiom.model.actors;

import ua.axiom.model.Role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "drivers")
public class Driver extends User {

    public Driver() {
    }

    @Column(name = "car_id")
    private long carId;

    private BigDecimal balance;

    @Column(name = "current_order_id")
    private Long currentOrderId;

    public long getCurrentOrderId() {
        return currentOrderId;
    }

    public void setCurrentOrderId(Long currentOrderId) {
        this.currentOrderId = currentOrderId;
    }

    public BigDecimal getMoney() {
        return balance;
    }

    public void setMoney(BigDecimal money) {
        this.balance = money;
    }

    @Override
    public Role getRole() {
        return Role.DRIVER;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public long getCarId() {
        return carId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}
