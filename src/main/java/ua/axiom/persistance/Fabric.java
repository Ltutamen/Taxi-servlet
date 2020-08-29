package ua.axiom.persistance;

/**
 * Object, that can be restored from an array of strings
 * @param <T>
 */
public interface Fabric<T> {
    T fabricate(String[] params);
}
