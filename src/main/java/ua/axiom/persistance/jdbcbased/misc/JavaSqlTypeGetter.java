package ua.axiom.persistance.jdbcbased.misc;

import java.sql.Types;
import java.util.HashMap;

/**
 * Provides mapping from Class type to java.sql.Type types
 */
public class JavaSqlTypeGetter {
    private static final HashMap<Class<?>, Integer> CLASS_TO_SQL_TYPE_MAP = new HashMap();

    static {
        CLASS_TO_SQL_TYPE_MAP.put(Number.class, Types.DECIMAL);
        CLASS_TO_SQL_TYPE_MAP.put(Long.class, Types.DECIMAL);
        CLASS_TO_SQL_TYPE_MAP.put(Boolean.class, Types.BIT);
        CLASS_TO_SQL_TYPE_MAP.put(boolean.class, Types.BIT);
    }

    public static int getJavaSqlType(Class<?> cclass) {
        Integer result =  CLASS_TO_SQL_TYPE_MAP.get(cclass);
        if(result == null) {
            throw new IllegalStateException("No entry for class  + <" + cclass + "> in class " + JavaSqlTypeGetter.class.getName());
        }
        return result;
    }
}
