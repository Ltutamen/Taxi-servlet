package ua.axiom.util;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public class GenericSetBuilder<T> {
    private Set<T> presentElements;

    private Supplier<Set<T>> setSupplier;

    public GenericSetBuilder(Supplier<Set<T>> setSupplier) {
        this.setSupplier = setSupplier;
        presentElements = new HashSet<>();
    }

    public GenericSetBuilder() {
        this(HashSet::new);
    }

    public GenericSetBuilder<T> addElement(T elm) {
        presentElements.add(elm);
        return this;
    }

    public Set<T> build() {
        Set<T> set = setSupplier.get();

        set.addAll(presentElements);

        return set;
    }
}
