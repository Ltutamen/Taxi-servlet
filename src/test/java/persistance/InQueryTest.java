package persistance;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ua.axiom.model.actors.Car;
import ua.axiom.model.actors.Order;
import java.math.BigDecimal;
import java.util.Date;

/*
@Ignore
public class InQueryTest {
    private InQuery<Long, Order> inOrderQuery;

    @Before
    public void setQuery() {
         inOrderQuery = new InQuery<>(
                "orders",
                new SimpleDBConnectionProvider()
        );
    }

    @Test
    public void restInsert() {
        Order order = new Order();
        order.setID(1L);
        order.setcClass(Car.Class.PREMIUM);
        order.setDeparture("Multiplayer");
        order.setDestination("Singleplayer");
        order.setStatus(Order.Status.PENDING);
        order.setPrice(new BigDecimal("1100.0"));
        order.setDate(new Date());
        order.setClientId(1L);

        inOrderQuery.execute(order, order.getId());
    }
}
*/
