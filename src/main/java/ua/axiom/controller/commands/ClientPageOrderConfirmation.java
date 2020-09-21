package ua.axiom.controller.commands;

import ua.axiom.controller.Command;
import ua.axiom.core.annotations.RequestMapping;
import ua.axiom.core.annotations.Autowired;
import ua.axiom.model.actors.Client;
import ua.axiom.service.buisness.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/clientpage/confirmation")
public class ClientPageOrderConfirmation extends Command<Client> {
    @Autowired
    private OrderService orderService;


    @Override
    protected String executePost(HttpServletRequest request, HttpServletResponse response) {

        orderService.confirmByClient(Long.parseLong(request.getParameter("orderId")));

        return "redirect:/clientpage";
    }
}
