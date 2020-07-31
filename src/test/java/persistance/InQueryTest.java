package persistance;

import org.junit.Before;
import org.junit.Test;
import ua.axiom.core.Context;
import ua.axiom.model.actors.Car;
import ua.axiom.model.actors.Order;
import ua.axiom.persistance.database.SimpleDBConnectionProvider;
import ua.axiom.persistance.query.InQuery;

import java.math.BigDecimal;
import java.util.Date;

public class InQueryTest {
    private InQuery<Long, Order> inOrderQuery;

    @Before
    public void setQuery() {
         inOrderQuery = new InQuery<>(
                "orders",
                new String[]{
                        "id",
                        "client_id",
                        "driver_id",
                        "status",
                        "price",
                        "date",
                        "c_class",
                        "departure",
                        "destination",
                        "confirmed_by_client",
                        "confirmed_by_driver"
                },
                new SimpleDBConnectionProvider()
        );

    }

    @Test
    public void restInsert() {
        Order order = new Order(1L);
        order.setcClass(Car.Class.PREMIUM);
        order.setDeparture("Multiplayer");
        order.setDestination("Singleplayer");
        order.setStatus(Order.Status.PENDING);
        order.setPrice(new BigDecimal("1100.0"));
        order.setDate(new Date());
        order.setClient_id(1L);

        inOrderQuery.execute(order, order.getId());

    }
}
