package ua.axiom.service;

import ua.axiom.core.Context;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class GuiService {

    public void setNavbarData(HttpServletRequest request) {
        request.setAttribute("word.company-name", "Cool taxi company!");
        request.setAttribute("sentence.logged-as", "<todo login>");
        request.setAttribute("username", "<todo authentication>");
        request.setAttribute("current-locale", "<todo locales>");
    }

    public void setLocalisationText(Locale locale, String ... keys) {

    }
}
