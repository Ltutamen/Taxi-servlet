package ua.axiom.service.buisness;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ua.axiom.core.Context;
import ua.axiom.model.actors.Car;
import ua.axiom.model.actors.Client;
import ua.axiom.model.actors.Driver;
import ua.axiom.model.actors.Order;
import ua.axiom.persistance.query.IdGenerationQuery;
import ua.axiom.persistance.repository.impl.ClientRepository;
import ua.axiom.persistance.repository.impl.DriverRepository;
import ua.axiom.persistance.repository.impl.OrderRepository;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ua.axiom.persistance.PersistentFieldUtil.getFieldByName;

public class OrderService {
    private static Field[] ADD_NEW_ORDER_CLIENT_UPDATED_FIELDS;
    private static Field[] FINISH_ORDER_DRIVER_UPDATE_FIELDS;
    private static Field[] FINISH_ORDER_ORDER_UPDATE_FIELDS;

    private OrderRepository orderRepository;
    private ClientRepository clientRepository;
    private DriverRepository driverRepository;
    private CarService carService;

    private IdGenerationQuery idGenerationQuery;

    {
        orderRepository = Context.get(OrderRepository.class);
        clientRepository = Context.get(ClientRepository.class);
        driverRepository = Context.get(DriverRepository.class);
        idGenerationQuery = Context.get(IdGenerationQuery.class);

        carService = Context.get(CarService.class);

        try {
            Class<Client> clientClass = Client.class;
            ADD_NEW_ORDER_CLIENT_UPDATED_FIELDS = new Field[]{clientClass.getDeclaredField("money")};
            Class<Driver> driverClass = Driver.class;
            FINISH_ORDER_DRIVER_UPDATE_FIELDS = new Field[]{driverClass.getDeclaredField("money"), driverClass.getDeclaredField("currentOrderId")};
            Class<Order> orderClass = Order.class;
            FINISH_ORDER_ORDER_UPDATE_FIELDS = new Field[]{orderClass.getDeclaredField("status"), orderClass.getDeclaredField("destination"), orderClass.getDeclaredField("departure"), orderClass.getDeclaredField("c_class"), orderClass.getDeclaredField("client_id"), orderClass.getDeclaredField("date")};
        } catch (NoSuchFieldException nsme) {
            throw new RuntimeException(nsme.getMessage());
        }
    }

    public void addNewOrder(Client user, String departure, String destination, Car.Class aClass) {
        Order order = new Order(idGenerationQuery.execute());

        order.setStatus(Order.Status.PENDING);
        order.setDestination(destination);
        order.setDeparture(departure);
        order.setcClass(aClass);
        order.setClient_id(user.getId());
        order.setDate(new Date());

        BigDecimal price = new BigDecimal("500.00");
        order.setPrice(price);
        user.setMoney(user.getMoney().subtract(price));

        clientRepository.update(user, ADD_NEW_ORDER_CLIENT_UPDATED_FIELDS);
        orderRepository.save(order);
    }

    public void finishOrder(Order order) {
        order.setStatus(Order.Status.FINISHED);

        Driver driver = driverRepository.findOne(order.getDriver_id()).iterator().next();

        driver.setMoney(driver.getMoney().add(order.getPrice()));
        driver.setCurrentOrderId(null);

        driverRepository.update(driver, FINISH_ORDER_DRIVER_UPDATE_FIELDS);
        orderRepository.update(order, FINISH_ORDER_ORDER_UPDATE_FIELDS);

    }

    public void confirmByClient(long orderID) {
        Order order = orderRepository.findOne(orderID).iterator().next();

        order.setConfirmedByClient(true);

        orderRepository.update(order, new Field[]{getFieldByName("confirmedByClient", Order.class)});
    }

    public void confirmByDriver(long orderID) {
        Order order = orderRepository.findOne(orderID).iterator().next();

        order.setConfirmedByDriver(true);

        orderRepository.update(order, new Field[]{getFieldByName("confirmedByDriver", Order.class)});

    }

    public void cancelOrder() {
        throw new NotImplementedException();
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
}
