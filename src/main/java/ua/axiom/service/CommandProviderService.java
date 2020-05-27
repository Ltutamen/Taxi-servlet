package ua.axiom.service;

import ua.axiom.controller.commands.*;
import ua.axiom.controller.commands.Command;

import java.util.HashMap;

public class CommandProviderService {

    private final HashMap<String, Command> uriToCommand = new HashMap<>();

    public CommandProviderService() {
        uriToCommand.put("/clientpage", new ClientPageCommand());
        uriToCommand.put("/driverpage", null);
        uriToCommand.put("/adminpage", null);

        uriToCommand.put("/api/postloginredirect", new PostLoginCommand());

        uriToCommand.put("/login", new LoginPageCommand());
        uriToCommand.put("/error", new ErrorCommand());
        uriToCommand.put("/", new MainPageCommand());
        uriToCommand.put("/index", new MainPageCommand());
    }

    public Command getCommand(String url) {
        return uriToCommand.get(url);

    }


}
