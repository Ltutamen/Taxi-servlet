package ua.axiom.core.context;

import org.apache.log4j.Logger;
import ua.axiom.core.TypeMapper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Context holds instantiated and configured classes
 */
public class ApplicationContext {
    private static Logger logger = Logger.getLogger(ApplicationContext.class);

    private Map<Class<?>, Object> implementationCache = new ConcurrentHashMap<>();
    private TypeMapper mapper;

    public ApplicationContext(TypeMapper mapper) {
        logger.info("<" + getClass()  + "> constructor");
        this.mapper = mapper;
    }

    public boolean hasImplementation(Class<?> tClass) {
        Class<?> implType = mapper.getImplType(tClass);
        return implementationCache.containsKey(implType);
    }

    public <T> T getImplementation(Class<T> type) {
        logger.info("<" + type + "> getObject request");
        Class<? extends T> implType = mapper.getImplType(type);
        return (T)implementationCache.get(implType);
    }

    public <T> void addImplementation(Class<T> forClass, T implementation) {
        implementationCache.put(forClass, implementation);
    }
}
