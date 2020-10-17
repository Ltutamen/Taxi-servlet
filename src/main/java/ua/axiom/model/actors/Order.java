package ua.axiom.model.actors;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    public enum Status{PENDING, TAKEN, FINISHED, CANCELLED};

    @Id
    private Long id;

    public Order() {
    }

    private Long clientId;

    private Long driverId;

    // @PersistingStrategy(strategy = PersistingDepersistingStrategyProvider.ORDINAL)
    private Status status;

    private BigDecimal price;

    private Date date;

    //  @PersistingStrategy(strategy = PersistingDepersistingStrategyProvider.ORDINAL)
    @Column(name = "c_class")
    private Car.Class cClass;

    private String departure;

    private String destination;

    @Column(name = "confirmed_by_client")
    private boolean confirmedByClient;

    @Column(name = "confirmed_by_driver")
    private boolean confirmedByDriver;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driver_id) {
        this.clientId = driver_id;
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
        return cClass;
    }

    public void setcClass(Car.Class cClass) {
        this.cClass = cClass;
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
        return confirmedByClient;
    }

    public void setConfirmedByClient(boolean confirmedByClient) {
        this.confirmedByClient = confirmedByClient;
    }

    public boolean isConfirmedByDriver() {
        return confirmedByDriver;
    }

    public void setConfirmedByDriver(boolean confirmedByDriver) {
        this.confirmedByDriver = confirmedByDriver;
    }

    public static List<OrderInputDescription> getOrderInputDescriptions() {
        return OrderInputDescription.inputDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
