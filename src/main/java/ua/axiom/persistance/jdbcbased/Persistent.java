package ua.axiom.persistance.jdbcbased;

import javax.persistence.Id;
import java.lang.reflect.Field;

/**
 * Object, that can be persisted with a key of type K
 * @param <K> key type
 */
public abstract class Persistent<K> {
    @Id
    protected final K id;

    protected Persistent(K id) {
        this.id = id;
    }

    public K getId() { return id; }

    public int getFieldsNum() {
        return PersistentFieldUtil.getAllFieldsAndSetAccessible(this).length;
    }

    public Field[] getOrderedFields() {
        return PersistentFieldUtil.getAllFieldsAndSetAccessible(this);
    }

}