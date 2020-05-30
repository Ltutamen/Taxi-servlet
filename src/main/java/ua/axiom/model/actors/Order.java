package ua.axiom.model.actors;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Order {
    public enum Status{PENDING, TAKEN, FINISHED};

    private Long id;

    private User client;

    private User driver;

    private Status status;

    private BigDecimal price;

    private Date date;

    private Car.Class cClass;

    private String departure;

    private String destination;

    private boolean confirmedByClient;

    private boolean confirmedByDriver;

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
