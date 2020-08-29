package ua.axiom.persistance.misc.representation;

import ua.axiom.persistance.misc.representation.depersision.EnumDepersistingStrategy;
import ua.axiom.persistance.misc.representation.persision.EnumPersistingStrategy;

public enum  PersistingDepersistingStrategyProvider {
    ORDINAL(EnumDepersistingStrategy.ORDINAL, EnumPersistingStrategy.ORDINAL),
    STRING(EnumDepersistingStrategy.STRING, EnumPersistingStrategy.ORDINAL);

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
