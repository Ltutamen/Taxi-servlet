package ua.axiom.service;

import ua.axiom.core.Context;
import ua.axiom.model.UserLocale;
import ua.axiom.model.actors.User;

import java.util.ArrayList;
import java.util.Map;

public class GuiService {
    private LocalisationService localisationService = Context.get(LocalisationService.class);

    public void userSpecificModelPopulation(Map<String, Object> model, User user) {
        nonUserSpecificModelPopulation(model, user.getLocale());

        model.put("username", user.getUsername());
        model.put("current-locale", user.getLocale());
        model.put("locales", UserLocale.values());
    }

    public void nonUserSpecificModelPopulation(Map<String, Object> model, UserLocale locale) {
        localisationService.setLocalisedMessages(model,
                locale.toJavaLocale(),
                "word.submit",
                "word.logout",
                "sentence.logged-welcome",
                "sentence.logged-as",
                "word.logout",
                "word.company-name",
                "sentence.logged-as",
                "word.company-name"
        );

        model.put("username", "");
        model.put("current-locale", "");
        model.put("locales", new ArrayList<>(0));
    }



}
