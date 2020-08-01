package ua.axiom.persistance;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Object, that can be persisted with a key of type K
 * @param <K> key type
 */
public abstract class Persistent<K> {
    protected final K id;

    public Persistent(K id) {
        this.id = id;
    }

    public K getId() { return id; }

    //  fields cache, to prevent calculation on each cal;
    private Field[] fields = null;

    //
    public int getFieldsNum() {
        return getOrderedFields().length;
    }

    public Field[] getOrderedFields() {
        if(fields == null) {
            synchronized (this) {
                if(fields == null) {
                    List<Field[]> fieldsList = new ArrayList<>();
                    for (Class cclass = this.getClass() ; cclass != null ; cclass = cclass.getSuperclass()) {
                        fieldsList.add(cclass.getDeclaredFields());
                    }
                    //  flatten list
                    fields = fieldsList.stream().flatMap(Arrays::stream).toArray(Field[]::new);
                }
            }
        }

        return fields;
    }
}
