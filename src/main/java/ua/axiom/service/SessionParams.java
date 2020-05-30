package ua.axiom.service;

public enum SessionParams {
    ROLE("role"),
    USER_ID("user_id");


    SessionParams(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    private final String name;

}
