package ua.axiom.controller;

import ua.axiom.core.Context;
import ua.axiom.model.UserLocale;
import ua.axiom.model.actors.Client;
import ua.axiom.persistance.repository.ClientRepository;
import ua.axiom.service.GuiService;
import ua.axiom.service.LocalisationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/clientpage")
public class ClientPageController extends HttpServlet {
    private GuiService guiService;
    private LocalisationService localisationService;
    private ClientRepository clientRepository = Context.get(ClientRepository.class);

    @Override
    public void init() throws ServletException {
        super.init();
        guiService = (GuiService) Context.get(GuiService.class);
        localisationService = (LocalisationService) Context.get(LocalisationService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> model = new HashMap<>();
        fillLocalisedPageData(model, UserLocale.DEFAULT_LOCALE);

        req.setAttribute("model", model);

        getServletContext().getRequestDispatcher("/appPages/clientpage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
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
