package ua.axiom.model.actors;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

public class Client extends User {

    private Date birthDate;

    private Date lastDiscountGiven;

    private BigDecimal money;

    private Collection<Discount> discount;

    private boolean receivedBDayPromoToday;

    public Client(long id) {
        super(id);
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
