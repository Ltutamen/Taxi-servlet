package ua.axiom.persistance.jdbcbased.misc.representation;

import ua.axiom.persistance.jdbcbased.misc.representation.depersision.EnumDepersistingStrategy;
import ua.axiom.persistance.jdbcbased.misc.representation.persision.EnumPersistingStrategy;

public enum  PersistingDepersistingStrategyProvider {
    ORDINAL(EnumDepersistingStrategy.ORDINAL, EnumPersistingStrategy.ORDINAL),
    STRING(EnumDepersistingStrategy.STRING, EnumPersistingStrategy.STRING);

    private EnumDepersistingStrategy enumDepersistingStrategy;
    private EnumPersistingStrategy enumPersistingStrategy;

    PersistingDepersistingStrategyProvider(EnumDepersistingStrategy enumDepersistingStrategy, EnumPersistingStrategy enumPersistingStrategy) {
        this.enumDepersistingStrategy = enumDepersistingStrategy;
        this.enumPersistingStrategy = enumPersistingStrategy;
    }

    public EnumPersistingStrategy getPersistingStrategy() {
        return enumPersistingStrategy;
    }

    public EnumDepersistingStrategy getDepersistingStrategy() {
        return enumDepersistingStrategy;
    }
}
