package ua.axiom.persistance.misc.representation;

import ua.axiom.persistance.misc.annotations.PersistingStrategy;

import java.lang.reflect.Field;
import java.util.function.Function;

public enum EnumPersistingStrategy implements Function<Object, Object> {
    ORDINAL(e -> Long.toString(e.ordinal())),
    STRING(Enum::name);

    private static final EnumPersistingStrategy DEFAULT_STRATEGY = ORDINAL;

    private Function<Enum, String> function;

    EnumPersistingStrategy(Function<Enum, String> function) {
        this.function = function;

    }

    @Override
    public String apply(Object fieldValue) {
        return function.apply((Enum)fieldValue);
    }

    public static String getRepresentation(Field field, Enum object) {
        PersistingStrategy annotation = field.getAnnotation(PersistingStrategy.class);
        EnumPersistingStrategy strategy = annotation != null ? annotation.strategy().getPersistingStrategy() : DEFAULT_STRATEGY;

        return strategy.apply(object);
    }

}
