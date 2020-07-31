package ua.axiom.model.actors.factories;

import ua.axiom.model.actors.Car;
import ua.axiom.model.actors.Order;
import ua.axiom.persistance.Fabricable;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class OrderFactory implements Fabricable<Order> {
    @Override
    public Order fabricate(String[] params) {
        Order order = new Order(Long.parseLong(params[1]));
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        order.setcClass(Car.Class.values()[Integer.parseInt(params[2])]);
        order.setConfirmedByClient(params[3].equals("1"));
        order.setConfirmedByDriver(params[4].equals("1"));
        order.setDeparture(params[6]);
        order.setDestination(params[7]);
        order.setPrice(new BigDecimal(params[8]));
        order.setStatus(Order.Status.valueOf(params[9]));
        order.setClient_id(Long.parseLong(params[10]));
        order.setDriver_id(params.length >= 11 ? Long.parseLong(params[10]) : null);
        try {
            order.setDate(dateFormat.parse(params[5]));
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }

        //  order.setCClass(params[2])
        return order;
    }
}
