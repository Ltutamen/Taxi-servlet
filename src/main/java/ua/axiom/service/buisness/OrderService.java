package ua.axiom.service.buisness;

import ua.axiom.core.annotations.Autowired;
import ua.axiom.core.annotations.Component;
import ua.axiom.model.actors.Car;
import ua.axiom.model.actors.Client;
import ua.axiom.model.actors.Driver;
import ua.axiom.model.actors.Order;
import ua.axiom.model.exception.NotEnoughMoneyException;
import ua.axiom.persistance.dao.ClientDao;
import ua.axiom.persistance.dao.DriverDao;
import ua.axiom.persistance.dao.OrderDao;
import ua.axiom.persistance.jdbcbased.query.IdGenerationQuery;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ua.axiom.persistance.jdbcbased.PersistentFieldUtil.getFieldByName;

@Component
public class OrderService {
    private static final Field[] ADD_NEW_ORDER_CLIENT_UPDATED_FIELDS;
    private static final Field[] FINISH_ORDER_DRIVER_UPDATE_FIELDS;
    private static final Field[] FINISH_ORDER_ORDER_UPDATE_FIELDS;
    private static final Field[] ORDER_TAKEN_BY_DRIVER_DRIVER_UPDATE_FIELDS;
    private static final Field[] ORDER_TAKEN_BY_DRIVER_ORDER_UPDATE_FIELDS;
    private static final Field[] ORDER_CANCELLED_ORDER_UPDATE_FIELDS;

    @Autowired
    private OrderDao orderRepository;
    @Autowired
    private ClientDao clientRepository;
    @Autowired
    private DriverDao driverRepository;
    @Autowired
    private CarService carService;

    @Autowired
    private IdGenerationQuery idGenerationQuery;

    static {
        try {
            Class<Client> clientClass = Client.class;
            Class<Order> orderClass = Order.class;
            Class<Driver> driverClass = Driver.class;

            ADD_NEW_ORDER_CLIENT_UPDATED_FIELDS = new Field[]{clientClass.getDeclaredField("money")};
            FINISH_ORDER_DRIVER_UPDATE_FIELDS = new Field[]{driverClass.getDeclaredField("balance"), driverClass.getDeclaredField("current_order_id")};
            FINISH_ORDER_ORDER_UPDATE_FIELDS = new Field[]{ orderClass.getDeclaredField("status")};
            ORDER_TAKEN_BY_DRIVER_DRIVER_UPDATE_FIELDS = new Field[] {driverClass.getDeclaredField("current_order_id")};
            ORDER_TAKEN_BY_DRIVER_ORDER_UPDATE_FIELDS = new Field[] {orderClass.getDeclaredField("status"), orderClass.getDeclaredField("driver_id")};
            ORDER_CANCELLED_ORDER_UPDATE_FIELDS = new Field[] {orderClass.getDeclaredField("status")};

        } catch (NoSuchFieldException nsme) {
            throw new RuntimeException(nsme.getMessage());
        }
    }

    public void addNewOrder(Client user, String departure, String destination, Car.Class aClass) throws NotEnoughMoneyException {
        BigDecimal price = new BigDecimal("500.00");

        if(user.getMoney().compareTo(price) < 0) {
            throw new NotEnoughMoneyException();
        }
        Order order = new Order(idGenerationQuery.execute());

        order.setStatus(Order.Status.PENDING);
        order.setDestination(destination);
        order.setDeparture(departure);
        order.setcClass(aClass);
        order.setClient_id(user.getId());
        order.setDate(new Date());

        order.setPrice(price);
        user.setMoney(user.getMoney().subtract(price));

        clientRepository.update(user, ADD_NEW_ORDER_CLIENT_UPDATED_FIELDS);
        orderRepository.save(order);
    }

    //  todo @Transactional
    public void processOrderTakenByDriver(long driverId, long orderId) {
        Driver driver = driverRepository.read(driverId);
        Order order = orderRepository.read(orderId);

        driver.setCurrentOrderId(order.getId());
        order.setStatus(Order.Status.TAKEN);
        order.setDriver_id(driverId);

        driverRepository.update(driver, ORDER_TAKEN_BY_DRIVER_DRIVER_UPDATE_FIELDS);
        orderRepository.update(order, ORDER_TAKEN_BY_DRIVER_ORDER_UPDATE_FIELDS);

    }

    public void tryFinishOrder(long orderId) {
        Order order = orderRepository.read(orderId);

        if(order.isConfirmedByClient() && order.isConfirmedByDriver()) {
            Driver driver = driverRepository.read(order.getDriver_id());

            driver.setMoney(driver.getMoney().add(order.getPrice()));
            driver.setCurrentOrderId(null);

            order.setStatus(Order.Status.FINISHED);

            driverRepository.update(driver, FINISH_ORDER_DRIVER_UPDATE_FIELDS);
            orderRepository.update(order, FINISH_ORDER_ORDER_UPDATE_FIELDS);
        }

    }

    public void confirmByClient(long orderID) {
        Order order = orderRepository.read(orderID);

        order.setConfirmedByClient(true);

        orderRepository.update(order, new Field[]{getFieldByName("confirmed_by_client", Order.class)});

        tryFinishOrder(orderID);
    }

    public void confirmByDriver(long orderID) {
        Order order = orderRepository.read(orderID);

        order.setConfirmedByDriver(true);

        orderRepository.update(order, new Field[]{getFieldByName("confirmed_by_driver", Order.class)});

        tryFinishOrder(orderID);

    }

    public void cancelOrder(String orderToCancelId) {
        Order order = orderRepository.read(Long.parseLong(orderToCancelId));

        order.setStatus(Order.Status.CANCELLED);

        orderRepository.update(order, ORDER_CANCELLED_ORDER_UPDATE_FIELDS);

    }

    public List<Order> getOrdersByStatusAndClass(Order.Status status, Car.Class cClass) {
        return orderRepository.findByFields(
                Stream.of(new Object[][]{
                {"status", status.toString()},
                {"c_class", cClass.toString()}})
                        .collect(Collectors.toMap(p -> (String)p[0], p -> p[1])));
    }

    public List<Order> getClientPendingOrders(long clientID) {
        return orderRepository.findByFields(
                Stream.of(
                    new Object[][] {
                            {"client_id", Long.toString(clientID)},
                            {"status", Order.Status.PENDING}
                    }
                    ).collect(Collectors.toMap(p -> (String)p[0], p -> p[1])));
    }

    public List<Order> getClientTakenOrders(long clientID) {
        return orderRepository.findByFields(
                Stream.of(
                    new Object[][] {
                            {"client_id", Long.toString(clientID)},
                            {"status", Order.Status.TAKEN}
                    }
                    ).collect(Collectors.toMap(p -> (String)p[0], p -> p[1])));
    }

    public List<Order> getSuitableOrders(Driver driver) {
        long carID = driver.getCarId();
        Car car = carService.getCarById(carID);
        Car.Class cClass = car.getaClass();

        return orderRepository.findByFields(Arrays.stream(new Object[][] {
                {"c_class", cClass},
                {"status", Order.Status.PENDING}
        }).collect(Collectors.toMap(p ->(String)p[0], p -> p[1])));
    }

    public Optional<Order> getOrderForDriver(long driverId) {
        return Optional.ofNullable(
                orderRepository.findByFields(Arrays.stream(new Object[][] {
                {"status", Order.Status.TAKEN},
                {"driver_id", driverId}
        }).collect(Collectors.toMap(p ->(String)p[0], p -> p[1]))).iterator().next());
    }
}
