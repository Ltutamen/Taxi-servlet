package ua.axiom.core;

/**
 * Maps interface type to implementaion type
 */
public interface TypeMapper {
    <T> Class<? extends T> getImplType(Class<T> forClass);
}
