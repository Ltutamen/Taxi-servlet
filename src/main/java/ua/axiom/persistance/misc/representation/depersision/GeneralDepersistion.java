package ua.axiom.persistance.misc.representation.depersision;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class GeneralDepersistion {
    private static final Map<Class, BiFunction<Field, String, Enum>> OBJECT_DEPERSISTION_STRATEGIES = new HashMap<>();

    static {
        OBJECT_DEPERSISTION_STRATEGIES.put(Enum.class, (f, o) -> EnumDepersistingStrategy.getRepresentation(f, o));
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
    }
}
