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

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Component
public class OrderService {

    @Autowired
    private OrderDao orderRepository;
    @Autowired
    private ClientDao clientRepository;
    @Autowired
    private DriverDao driverRepository;
    @Autowired
    private CarService carService;

    public void addNewOrder(Client user, String departure, String destination, Car.Class aClass) throws NotEnoughMoneyException {
        BigDecimal price = new BigDecimal("500.00");

        if(user.getMoney().compareTo(price) < 0) {
            throw new NotEnoughMoneyException();
        }
        Order order = new Order();

        order.setStatus(Order.Status.PENDING);
        order.setDestination(destination);
        order.setDeparture(departure);
        order.setcClass(aClass);
        order.setClientId(user.getId());
        order.setDate(new Date());

        order.setPrice(price);
        user.setMoney(user.getMoney().subtract(price));

        clientRepository.update(user);
        orderRepository.save(order);
    }

    //  todo @Transactional
    public void processOrderTakenByDriver(long driverId, long orderId) {
        Driver driver = driverRepository.read(driverId);
        Order order = orderRepository.read(orderId);

        driver.setCurrentOrderId(order.getId());
        order.setStatus(Order.Status.TAKEN);
        order.setDriverId(driverId);

        driverRepository.update(driver);
        orderRepository.update(order);

    }

    public void tryFinishOrder(long orderId) {
        Order order = orderRepository.read(orderId);

        if(order.isConfirmedByClient() && order.isConfirmedByDriver()) {
            Driver driver = driverRepository.read(order.getDriverId());

            driver.setMoney(driver.getMoney().add(order.getPrice()));
            driver.setCurrentOrderId(null);

            order.setStatus(Order.Status.FINISHED);

            driverRepository.update(driver);
            orderRepository.update(order);
        }

    }

    public void confirmByClient(long orderID) {
        Order order = orderRepository.read(orderID);

        order.setConfirmedByClient(true);

        orderRepository.update(order);

        tryFinishOrder(orderID);
    }

    public void confirmByDriver(long orderID) {
        Order order = orderRepository.read(orderID);

        order.setConfirmedByDriver(true);

        orderRepository.update(order);

        tryFinishOrder(orderID);

    }

    public void cancelOrder(String orderToCancelId) {
        Order order = orderRepository.read(Long.parseLong(orderToCancelId));

        order.setStatus(Order.Status.CANCELLED);

        orderRepository.update(order);

    }

    public Collection<Order> getOrdersByStatusAndClass(Order.Status status, Car.Class cClass) {
        return orderRepository.getByStatusAndCarClass(status, cClass);
    }

    public Collection<Order> getClientPendingOrders(long clientID) {
        return orderRepository.getByClientAndStatus(clientID, Order.Status.PENDING);
    }

    public Collection<Order> getClientTakenOrders(long clientID) {
        return orderRepository.getByClientAndStatus(clientID, Order.Status.TAKEN);
    }

    public Collection<Order> getSuitableOrders(Driver driver) {
        long carID = driver.getCarId();
        Car car = carService.getCarById(carID);
        Car.Class cClass = car.getaClass();

        return orderRepository.getByStatusAndCarClass(Order.Status.PENDING, cClass);
    }

    public Collection<Order> getOrderForDriverAndStatus(long driverId, Order.Status status) {
        return orderRepository.getByDriverAndStatus(driverId, status);
    }
}
