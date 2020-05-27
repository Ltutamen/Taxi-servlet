package ua.axiom.controller.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Command {
    public abstract String execute(HttpServletRequest request, HttpServletResponse response, String uri) throws IOException, ServletException;

}
