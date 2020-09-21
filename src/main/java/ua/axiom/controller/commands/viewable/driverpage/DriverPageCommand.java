package ua.axiom.controller.commands.viewable.driverpage;

import ua.axiom.controller.commands.MultiViewCommand;
import ua.axiom.controller.commands.viewable.ErrorCommand;
import ua.axiom.core.annotations.Autowired;
import ua.axiom.core.annotations.InitMethod;
import ua.axiom.core.annotations.RequestMapping;
import ua.axiom.model.actors.Driver;
import ua.axiom.service.DriverSessionContextService;

@RequestMapping("/driverpage")
public class DriverPageCommand extends MultiViewCommand<Driver> {
    @Autowired
    private DriverSessionContextService driverSessionContextService;

    @Autowired
    private DriverWithOrderPageCommand driverWithOrderPageCommand;

    @Autowired
    private DriverNoOrderPageCommand driverNoOrderPageCommand;

    public DriverPageCommand() {
    }

    @InitMethod
    private void init() {
        super.addCommand(request -> request.getSession(false) == null, new ErrorCommand());
        super.addCommand(request -> driverSessionContextService.hasOrder(request.getSession()), driverWithOrderPageCommand);
        super.addCommand(request -> !driverSessionContextService.hasOrder(request.getSession()), driverNoOrderPageCommand);
    }
}
