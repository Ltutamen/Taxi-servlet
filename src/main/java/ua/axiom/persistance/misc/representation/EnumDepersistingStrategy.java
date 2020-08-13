package ua.axiom.persistance.misc.representation;

import ua.axiom.persistance.misc.annotations.PersistingStrategy;

import java.lang.reflect.Field;
import java.util.function.BiFunction;

public enum EnumDepersistingStrategy implements BiFunction<String, Class<? extends Enum<?>>, Enum<?>> {
    ORDINAL((ls, eo) -> eo.getEnumConstants()[Integer.parseInt(ls)]),
    STRING((ls, ec) -> Enum.valueOf((Class)ec, ls));

    private static final EnumDepersistingStrategy DEFAULT_STRATEGY = ORDINAL;

    private BiFunction<String, Class<? extends Enum<?>>, Enum<?>> function;

    EnumDepersistingStrategy(BiFunction<String, Class<? extends Enum<?>>, Enum<?>> function) {
        this.function = function;
    }

    @Override
    public Enum<?> apply(String fieldValue, Class<? extends Enum<?>> enumClass) {
        return function.apply(fieldValue, enumClass);
    }

    public static <T extends Enum<T>> Enum<T> getRepresentation(Field field, String fieldValue) {
        PersistingStrategy annotation = field.getAnnotation(PersistingStrategy.class);
        EnumDepersistingStrategy strategy = annotation != null ? annotation.strategy().getDepersistingStrategy() : DEFAULT_STRATEGY;

        return (Enum<T>)strategy.apply(fieldValue, (Class<? extends Enum<?>>)field.getType());
    }
}

