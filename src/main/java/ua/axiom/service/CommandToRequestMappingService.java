package ua.axiom.service;

import ua.axiom.controller.Command;
import ua.axiom.controller.commands.*;
import ua.axiom.controller.commands.viewable.driverpage.DriverNoOrderPageCommand;
import ua.axiom.controller.commands.viewable.driverpage.DriverPageCommand;
import ua.axiom.controller.commands.viewable.mainpage.MainPageCommand;
import ua.axiom.controller.commands.viewable.*;
import ua.axiom.model.actors.User;

import java.util.HashMap;

public class CommandToRequestMappingService {

    private final HashMap<String, Command> uriToCommand = new HashMap<>();

    public CommandToRequestMappingService() {
        uriToCommand.put("/clientpage", new ClientPageCommand());
        uriToCommand.put("/driverpage", new DriverPageCommand());
        uriToCommand.put("/adminpage", new AdminPageCommand());

        uriToCommand.put("/neworder", new NewOrderCommand());
        uriToCommand.put("/api/neworder", new NewOrderPostCommand());

        uriToCommand.put("/api/postloginredirect", new PostLoginCommand());

        uriToCommand.put("/login", new LoginPageCommand());
        uriToCommand.put("/logout", new LogoutCommand());
        uriToCommand.put("/error", new ErrorCommand());
        uriToCommand.put("/", new MainPageCommand());
        uriToCommand.put("/index", new MainPageCommand());

        uriToCommand.put("/driverpage/takeorder", new DriverPageTakeOrder());
        uriToCommand.put("/driverpage/confirmation", new DriverPageOrderConfirmation());
    }

    public Command<? extends User> getCommand(String url) {
        return uriToCommand.get(url);
    }


}
