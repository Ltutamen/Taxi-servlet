package ua.axiom.model.actors;

import ua.axiom.model.Role;
import ua.axiom.model.UserLocale;
import ua.axiom.persistance.Persistent;

public abstract class User extends Persistent<Long> {

    protected String username;

    protected String password;

    public abstract Role getRole();

    protected UserLocale locale;

    protected boolean isBanned;

    public User(long id) {
        super(id);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserLocale getLocale() {
        return locale;
    }

    public void setLocale(UserLocale locale) {
        this.locale = locale;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }
}
