package ua.axiom.model.actors.factories;

import ua.axiom.model.UserLocale;
import ua.axiom.model.actors.Client;
import ua.axiom.repository.Fabricable;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientFactory implements Fabricable<Client> {
    @Override
    public Client fabricate(String[] params) {

        Client client = new Client(Long.parseLong(params[1]));
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        client.setBanned(params[2].equals("1"));
        client.setUsername(params[5]);
        client.setPassword(params[4]);
        client.setMoney(new BigDecimal(params[8]));
        client.setLocale(UserLocale.valueOf(params[3]));
        try {
            client.setBirthDate(dateFormat.parse(params[6]));
            client.setLastDiscountGiven(dateFormat.parse(params[7]));
        } catch (ParseException parseException) {
            System.err.println("exception parsing string from the database as date as <" + dateFormat.getNumberFormat() + "> at pos:" + parseException.getErrorOffset());
            System.exit(1);
        }

        return client;
    }
}
