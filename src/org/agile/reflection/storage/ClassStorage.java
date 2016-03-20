package org.agile.reflection.storage;

import org.agile.reflection.loader.ByteClassLoader;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Francis(AgileTM)
 * Date: 26/03/13
 * Time: 5:01 PM
 * Project: ${PROJECT_NAME}
 * Package: ${PACKAGE_NAME}
 */
public class ClassStorage {

    public static Map<String, Class<?>> classes = new HashMap<>();
    public static ByteClassLoader loader;

    public static Class<?> getClass(final String name) {
        if (classes.containsKey(name)) {
            return classes.get(name);
        }
        if (loader != null && loader.classes.containsKey(name)) {
            loader.loadClass(name);
            return classes.get(name);
        }
        return null;
    }

    public static int getSize() {
        return classes.size();
    }

    public static void setClassLoader(final ByteClassLoader loader) {
        ClassStorage.loader = loader;
    }

}
