package ua.axiom.controller.commands.viewable;

import ua.axiom.controller.Command;
import ua.axiom.core.annotations.Component;
import ua.axiom.core.annotations.Autowired;
import ua.axiom.core.annotations.RequestMapping;
import ua.axiom.model.UserLocale;
import ua.axiom.model.actors.Client;
import ua.axiom.service.GuiService;
import ua.axiom.service.LocalisationService;
import ua.axiom.service.buisness.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RequestMapping("/clientpage")
@Component
public class ClientPageCommand extends Command<Client> {
    @Autowired
    private GuiService guiService;
    @Autowired
    private LocalisationService localisationService;
    @Autowired
    private OrderService orderService;

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
                "sentence.sentence-confirm-msg",
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
    protected void userSpecificDataFill(Map<String, Object> model, Client client) {
        model.put("client_balance", client.getMoney());
        model.put("taken_orders", orderService.getClientTakenOrders(client.getId()));
        model.put("pending_orders", orderService.getClientPendingOrders(client.getId()));
        guiService.userSpecificModelPopulation(model, client);
    }
}

