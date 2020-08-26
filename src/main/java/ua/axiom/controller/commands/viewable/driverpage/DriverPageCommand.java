package ua.axiom.controller.commands.viewable.driverpage;

import ua.axiom.controller.commands.MultiViewCommand;
import ua.axiom.controller.commands.viewable.ErrorCommand;
import ua.axiom.core.Context;
import ua.axiom.service.DriverSessionContextService;

public class DriverPageCommand extends MultiViewCommand {
    private DriverSessionContextService driverSessionContextService;

    {
        driverSessionContextService = Context.get(DriverSessionContextService.class);
    }

    public DriverPageCommand() {
        super.addCommand(request -> request.getSession(false) == null, new ErrorCommand());
        super.addCommand(request -> driverSessionContextService.hasOrder(request.getSession()), new DriverWithOrderPageCommand());
        super.addCommand(request -> !driverSessionContextService.hasOrder(request.getSession()), new DriverNoOrderPageCommand());
    }
}
