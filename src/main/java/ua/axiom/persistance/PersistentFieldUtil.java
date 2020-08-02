package ua.axiom.persistance;

import java.lang.reflect.Field;
import java.util.*;

public class PersistentFieldUtil {
    private static final Map<Class<? extends Persistent>, Field[]> cachedField = new HashMap<>();

    public static Field[] getAllFields(Persistent<?> object) {
        if(!cachedField.containsKey(object.getClass())) {
            synchronized (cachedField) {
                if(!cachedField.containsKey(object.getClass())) {
                    List<Field[]> fieldsList = new ArrayList<>();
                    for (Class cclass = object.getClass() ; cclass != null ; cclass = cclass.getSuperclass()) {
                        fieldsList.add(cclass.getDeclaredFields());
                    }
                    //  flatten list
                    cachedField.put(object.getClass(), fieldsList.stream().flatMap(Arrays::stream).toArray(Field[]::new));
                }
            }
        }

        return cachedField.get(object.getClass());
    }
}
