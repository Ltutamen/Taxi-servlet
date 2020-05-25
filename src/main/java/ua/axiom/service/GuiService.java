package ua.axiom.service;

import ua.axiom.core.Context;
import ua.axiom.model.UserLocale;
import ua.axiom.model.actors.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Map;

public class GuiService {
    private LocalisationService localisationService = (LocalisationService) Context.get(LocalisationService.class);

    public void setNavbarData(HttpServletRequest request) {
        request.setAttribute("word.company-name", "Cool taxi company!");
        request.setAttribute("sentence.logged-as", "<todo login>");
        request.setAttribute("username", "<todo authentication>");
        request.setAttribute("current-locale", "<todo locales>");
    }

    public void populateModelWithNavbarData(Map<String, Object> model, User user) {

        localisationService.setLocalisedMessages(
                model,
                user.getLocale().toJavaLocale(),
                "sentence.driver-page-desc",    //  ?
                "sentence.new-order-request-msg",
                "word.submit",
                "word.logout",
                "sentence.logged-welcome",
                "sentence.logged-as",
                "word.logout",
                "word.company-name"
        );

        model.put("username", user.getUsername());
        model.put("current-locale", user.getLocale());
        model.put("locales", UserLocale.values());
    }

    public void setLocalisationText(Locale locale, String... keys) {

    }
}
