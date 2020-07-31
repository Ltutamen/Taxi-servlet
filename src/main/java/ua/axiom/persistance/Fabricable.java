package ua.axiom.persistance;

/**
 * Object, that can be restored from an ordered set of strings
 * @param <T>
 */
public interface Fabricable<T> {
    T fabricate(String[] params);
}
