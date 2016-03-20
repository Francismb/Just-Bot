package org.agile.reflection.storage.type;

import org.agile.reflection.storage.ClassStorage;
import org.objectweb.asm.tree.FieldNode;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Francis
 * Date: 25/03/13
 * Time: 8:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClassIdentity {

    private final String name;
    private final String className;
    private final List<FieldIdentity> fields = new ArrayList<>();

    public ClassIdentity(final String name, final String className) {
        this.name = name;
        this.className = className;
    }

    public void addIdentity(final FieldIdentity identity) {
        fields.add(identity);
    }

    public FieldIdentity getIdentity(final String name) {
        for (final FieldIdentity identity : fields) {
            if (identity.getName().equals(name)) {
                return identity;
            }
        }
        return null;
    }

    public FieldIdentity[] getIdentities() {
        return fields.toArray(new FieldIdentity[fields.size()]);
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }

    public Class<?> loadClass() {
        return ClassStorage.getClass(className);
    }

    public Class<?> loadClass(final FieldIdentity identity) {
        try {
            return ClassStorage.getClass(identity.getOwner());
        } catch (final NullPointerException e) {
            System.out.println("Couldn't load class : " + identity.getOwner());
        }
        return null;
    }

    public Field getField(final FieldIdentity identity) {
        if (identity == null) {
            throw new RuntimeException("Couldnt Find Identity");
        }
        final Class<?> clazz = loadClass(identity);
        if (clazz != null) {
            try {
                final Field field = clazz.getDeclaredField(identity.getFieldName());
                if (field != null) {
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    return field;
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Field[] getFields() {
        return loadClass().getFields();
    }

    public Method[] getMethods() {
        return loadClass().getMethods();
    }

}
