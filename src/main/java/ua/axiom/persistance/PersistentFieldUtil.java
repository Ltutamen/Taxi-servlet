package ua.axiom.persistance;

import java.lang.reflect.Field;
import java.util.*;

public class PersistentFieldUtil {
    private static final Map<Class<? extends Persistent>, Field[]> cachedField = new HashMap<>();

    public static Field[] getAllFieldsAndSetAccessible(Persistent<?> object) {
        return getAllFieldsAndSetAccessible(object.getClass());
    }

    /**
     * @return all fields in given class, that are also set to accessible
     */
    public static Field[] getAllFieldsAndSetAccessible(Class<? extends Persistent> cclass) {
        if(!cachedField.containsKey(cclass)) {
            synchronized (cachedField) {
                if(!cachedField.containsKey(cclass)) {
                    List<Field[]> fieldsList = new ArrayList<>();
                    for (Class ccclass = cclass; ccclass != null ; ccclass = ccclass.getSuperclass()) {
                        fieldsList.add(ccclass.getDeclaredFields());
                    }
                    //  flatten list
                    cachedField.put(cclass, fieldsList
                            .stream()
                            .flatMap(Arrays::stream)
                            .map(field -> {field.setAccessible(true); return field;})
                            .toArray(Field[]::new));
                }
            }
        }

        return cachedField.get(cclass);
    }

    public static Field getFieldByName(String name, Class<? extends Persistent> cClass) {
        Field[] fields = getAllFieldsAndSetAccessible(cClass);

        for (Field field : fields) {
            if(field.getName().equals(name)) {
                return field;
            }
        }

        throw new IllegalStateException("Field <" + name + "> doesnt exist in class <" + cClass.getName() + ">");
    }
}
