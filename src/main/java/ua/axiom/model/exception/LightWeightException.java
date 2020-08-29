package ua.axiom.model.exception;

import java.io.IOException;

/**
 * Indicational exception, meant to be fast and to be catched
 */
public class LightWeightException extends IOException {
    @Override
    public synchronized Throwable fillInStackTrace() { return this; }
}
