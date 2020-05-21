package ua.axiom.persistance;

public abstract class Persistent<K> {
    protected final K id;

    public K getId() { return id; }

    public Persistent(K id) {
        this.id = id;
    }
}
