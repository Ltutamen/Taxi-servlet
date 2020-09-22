package ua.axiom.controller;

import ua.axiom.core.context.ApplicationContext;
import ua.axiom.service.CommandToRequestMappingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/**", "/"}, name = "mainPage")
public class Servlet extends HttpServlet {
    private final static String PACKAGE_TO_SCAN = "ua.axiom";

    //  the only Context lookup
    {
        ApplicationContext.init();
        commandProviderService = ApplicationContext.getInstance().getObject(CommandToRequestMappingService.class);
    }

    private CommandToRequestMappingService commandProviderService;

    @Override
    public void init() throws ServletException {

/*        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(PACKAGE_TO_SCAN))
                .setScanners(new SubTypesScanner(),
                        new TypeAnnotationsScanner()));
*/

        //  ApplicationContext.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();

        String next = commandProviderService.getCommand(requestURI).execute(req, resp, requestURI);

        if(next.contains("redirect:")) {
            resp.sendRedirect(next.replace("redirect:", ""));

        } else if(next.contains("forward:")) {
            getServletContext().getRequestDispatcher(next.replace("forward:", "")).forward(req, resp);

        } else {
            throw new IllegalArgumentException("Wrong result string: " + next);
        }

    }
}
