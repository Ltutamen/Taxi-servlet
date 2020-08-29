package ua.axiom.service.misc;

public interface TriConsumer<T, U, R> {
    void consume(T a, U b, R c);
}
