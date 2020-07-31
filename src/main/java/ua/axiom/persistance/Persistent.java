package ua.axiom.persistance;

/**
 * Object, that can be persisted with a key of type K
 * @param <K> key type
 */
public abstract class Persistent<K> {
    protected final K id;

    public K getId() { return id; }

    /**
     * @return quantity of persistable fields in object
     */
    public int getFieldsNum() {
        Class<? extends Persistent> cClass = this.getClass();

        return cClass.getFields().length;
    }

    public Persistent(K id) {
        this.id = id;
    }
}
