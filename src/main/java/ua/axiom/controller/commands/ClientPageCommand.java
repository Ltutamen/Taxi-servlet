package ua.axiom.controller.commands;

import ua.axiom.core.Context;
import ua.axiom.model.UserLocale;
import ua.axiom.persistance.repository.ClientRepository;
import ua.axiom.service.GuiService;
import ua.axiom.service.LocalisationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ClientPageCommand extends Command {
    private GuiService guiService;
    private LocalisationService localisationService;
    private ClientRepository clientRepository;

    {
        guiService = Context.get(GuiService.class);
        localisationService = Context.get(LocalisationService.class);
        clientRepository = Context.get(ClientRepository.class);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, String uri) throws IOException, ServletException {
        Map<String, Object> model = new HashMap<>();
        fillLocalisedPageData(model, UserLocale.DEFAULT_LOCALE);

        request.setAttribute("model", model);

        return "forward:/appPages/clientpage.jsp";
        //  request.getRequestDispatcher("").forward(request, response);
    }

    protected void fillLocalisedPageData(Map<String, Object> model, UserLocale userLocale) {
        //  guiService.populateModelWithNavbarData(model, );

        localisationService.setLocalisedMessages(
                model,
                userLocale.toJavaLocale(),
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

        //  model.put("order-history", orderRepository.findAll(PageRequest.of(0, 10)));
        //  model.put("new-order-details", Order.getOrderInputDescriptions());
    }
}
