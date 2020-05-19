package ua.axiom.repository;

public interface Fabricable<T> {
    T fabricate(String[] params);
}
