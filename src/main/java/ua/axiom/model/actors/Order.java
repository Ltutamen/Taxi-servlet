package ua.axiom.model.actors;

import ua.axiom.persistance.Persistent;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Order extends Persistent<Long> {
    public Order(Long id) {
        super(id);
    }

    public enum Status{PENDING, TAKEN, FINISHED};

    private Long client_id;

    private Long driver_id;

    private Status status;

    private BigDecimal price;

    private Date date;

    private Car.Class c_class;

    private String departure;

    private String destination;

    private boolean confirmed_by_client;

    private boolean confirmed_by_driver;

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public Long getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(Long driver_id) {
        this.driver_id = driver_id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Car.Class getcClass() {
        return c_class;
    }

    public void setcClass(Car.Class cClass) {
        this.c_class = cClass;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public boolean isConfirmedByClient() {
        return confirmed_by_client;
    }

    public void setConfirmedByClient(boolean confirmedByClient) {
        this.confirmed_by_client = confirmedByClient;
    }

    public boolean isConfirmedByDriver() {
        return confirmed_by_driver;
    }

    public void setConfirmedByDriver(boolean confirmedByDriver) {
        this.confirmed_by_driver = confirmedByDriver;
    }

    public static List<OrderInputDescription> getOrderInputDescriptions() {
        return OrderInputDescription.inputDescription;
    }

    private static class OrderInputDescription {
        static List<OrderInputDescription> inputDescription = Arrays.asList(
                new OrderInputDescription("from", "text"),
                new OrderInputDescription("where", "text")
        );

        public String name;
        public String type;

        public OrderInputDescription(String name, String type) {
            this.name = name;
            this.type = type;
        }
    }

}
