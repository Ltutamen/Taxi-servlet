package ua.axiom.service;

import ua.axiom.controller.commands.*;
import ua.axiom.controller.commands.mainpage.MainPageCommand;
import ua.axiom.model.actors.User;

import java.util.HashMap;

public class CommandProviderService {

    private final HashMap<String, Command> uriToCommand = new HashMap<>();

    public CommandProviderService() {
        uriToCommand.put("/clientpage", new ClientPageCommand());
        uriToCommand.put("/driverpage", new DriverPageCommand());
        uriToCommand.put("/adminpage", new AdminPageCommand());

        uriToCommand.put("/neworder", new NewOrderCommand());

        uriToCommand.put("/api/postloginredirect", new PostLoginCommand());

        uriToCommand.put("/login", new LoginPageCommand());
        uriToCommand.put("/logout", new LogoutCommand());
        uriToCommand.put("/error", new ErrorCommand());
        uriToCommand.put("/", new MainPageCommand());
        uriToCommand.put("/index", new MainPageCommand());
    }

    public Command<? extends User> getCommand(String url) {
        return uriToCommand.get(url);
    }


}
