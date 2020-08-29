package ua.axiom.persistance.misc.representation.persision;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class GeneralPersisting {
    private static final Map<Class<?>, BiFunction<Field, Object, String>> OBJECT_PERSISTING_STRATEGIES = new HashMap<>();

    static {
        OBJECT_PERSISTING_STRATEGIES.put(Number.class, (f, o) -> o.toString());
        OBJECT_PERSISTING_STRATEGIES.put(Enum.class, (f, o) -> EnumPersistingStrategy.getRepresentation(f, (Enum)o));
        OBJECT_PERSISTING_STRATEGIES.put(String.class, (f, o) -> o.toString());
        OBJECT_PERSISTING_STRATEGIES.put(Boolean.class, (f, o) -> o.toString());
        OBJECT_PERSISTING_STRATEGIES.put(boolean.class, (f, o) -> (boolean) o ? "b'1'" : "b'0'");
    }

    public static String getRepresentation(Field field, Object fieldValue) {
        return OBJECT_PERSISTING_STRATEGIES
                .entrySet()
                .stream()
                .filter(
                        entry -> entry
                                .getKey()
                                .isAssignableFrom(field.getType()))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("No entry for <" + field.getType() + ">"))
                .getValue()
                .apply(field, fieldValue);
    }

    public static Boolean getBoolRepresentation(Field field, Object object) {
        return (Boolean)object;
    }
}
