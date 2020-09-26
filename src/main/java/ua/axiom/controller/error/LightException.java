package ua.axiom.controller.error;

public class LightException extends Throwable {
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
