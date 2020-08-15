package ua.axiom.controller.commands;

import ua.axiom.controller.Command;
import ua.axiom.core.Context;
import ua.axiom.model.UserLocale;
import ua.axiom.model.actors.Client;
import ua.axiom.model.actors.Order;
import ua.axiom.persistance.repository.impl.ClientRepository;
import ua.axiom.persistance.repository.impl.OrderRepository;
import ua.axiom.service.GuiService;
import ua.axiom.service.LocalisationService;
import ua.axiom.service.buisness.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClientPageCommand extends Command<Client> {
    private GuiService guiService;
    private LocalisationService localisationService;
    private OrderService orderService;
    //  private ClientService clientService;

    {
        guiService = Context.get(GuiService.class);
        localisationService = Context.get(LocalisationService.class);
        orderService = Context.get(OrderService.class);
        //  orderRepository = Context.get(OrderRepository.class);
    }

    @Override
    protected String processGet(HttpServletRequest request, HttpServletResponse response) {
        return "forward:/appPages/clientpage.jsp";
    }

    @Override
    protected void nonUserSpecificDataFill(Map<String, Object> model, UserLocale locale) {
        localisationService.setLocalisedMessages(
                model,
                locale.toJavaLocale(),
                "word.hello",
                "word.menu",
                "sentence.new-order",
                "sentence.your-balance",
                "sentence.cancel-order",
                "sentence.promocodes",
                "sentence.replenish-balance",
                "sentence.delete-account",
                "info.username",
                "word.from",
                "word.to",
                "word.class",
                "word.fee",
                "word.page",
                "word.cancel",
                "word.balance",
                "sentence.your-orders",
                "sentence.order-history"
        );
    }

    @Override
    protected void userSpecificDataFill(Map<String, Object> model, Client user) {
        model.put("client_balance", user.getMoney());
        model.put("taken_orders", orderService.getClientTakenOrders(user.getId()));
        model.put("pending_orders", orderService.getClientPendingOrders(user.getId()));
        guiService.userSpecificModelPopulation(model, user);
    }
}

