package ua.axiom.model.actors;

import ua.axiom.model.Role;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

@Entity
public class Client extends User {

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    private Date birthDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastDiscountGiven;

    private BigDecimal money;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Discount.class)
    private Collection<Discount> discount;

    private boolean receivedBDayPromoToday;

    public Client() {
    }

    @Override
    public Role getRole() {
        return Role.CLIENT;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getLastDiscountGiven() {
        return lastDiscountGiven;
    }

    public void setLastDiscountGiven(Date lastDiscountGiven) {
        this.lastDiscountGiven = lastDiscountGiven;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Collection<Discount> getDiscount() {
        return discount;
    }

    public void setDiscount(Collection<Discount> discount) {
        this.discount = discount;
    }

    public boolean isReceivedBDayPromoToday() {
        return receivedBDayPromoToday;
    }

    public void setReceivedBDayPromoToday(boolean receivedBDayPromoToday) {
        this.receivedBDayPromoToday = receivedBDayPromoToday;
    }






}
