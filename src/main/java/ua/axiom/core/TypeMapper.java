package ua.axiom.core;

import java.util.Collection;

/**
 * Maps interface type to implementaion type
 */
public interface TypeMapper {
    <T> Class<? extends T> getImplType(Class<T> forClass);
}
