package ua.axiom.util;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * Returns collection of type U, that contains all elements from collection oldCollection
 */
public class GenericCollectionsUtil {
    public static <T, U extends Collection<T>> U toCollection(Supplier<U> newCollectionSupplier, Collection<?> oldCollection) {
        U newCollection = newCollectionSupplier.get();

        for(Object element : oldCollection) {
            newCollection.add((T)element);
        }

        return newCollection;
    }
}
