package ua.axiom.persistance;

public interface Fabricable<T> {
    T fabricate(String[] params);
}
