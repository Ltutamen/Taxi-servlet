package ua.axiom.persistance.misc.representation;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

//  todo split into two classes
//  todo make for every class, and deserialise objects by foe-each if Factories
public class GeneralPersisting {
    private static final Map<Class<?>, BiFunction<Field, Object, String>> OBJECT_PERSISTING_STRATEGIES = new HashMap<>();
    private static final Map<Class, BiFunction<Field, String, Enum>> OBJECT_DEPERSISTION_STRATEGIES = new HashMap<>();

    static {
        OBJECT_PERSISTING_STRATEGIES.put(Number.class, (f, o) -> o.toString());
        OBJECT_PERSISTING_STRATEGIES.put(Enum.class, (f, o) -> EnumPersistingStrategy.getRepresentation(f, (Enum)o));
        OBJECT_PERSISTING_STRATEGIES.put(String.class, (f, o) -> o.toString());

        OBJECT_DEPERSISTION_STRATEGIES.put(Enum.class, (f, o) -> EnumDepersistingStrategy.getRepresentation(f, o));


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
                .orElseThrow(IllegalStateException::new)
                .getValue()
                .apply(field, fieldValue);
    }

    /**
     * @param field specifies persistance type (threw annotations) and return enum type
     * @param value specifies value of enum field
     * @return
     */
    public static <T extends Enum<T>> Enum<T> getObject(Field field, String value) {
        return OBJECT_DEPERSISTION_STRATEGIES
                .entrySet()
                .stream()
                .filter(
                        entry -> entry
                                .getKey()
                                .isAssignableFrom(field.getType()))
                .findAny()
                .orElseThrow(IllegalStateException::new).getValue()
                .apply(field, value);
    };

}
