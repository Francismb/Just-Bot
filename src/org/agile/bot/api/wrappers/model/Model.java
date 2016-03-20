package org.agile.bot.api.wrappers.model;

import org.agile.bot.Global;
import org.agile.bot.api.utilities.Calculations;
import org.agile.reflection.storage.IdentityStorage;
import org.agile.reflection.storage.type.ClassIdentity;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

/**
 * User: Francis(AgileTM)
 * Date: 7/08/13
 * Time: 8:34 AM
 * Project: Client
 * Package: org.agile.bot.api.wrappers
 */
public class Model {

    private final Object instance;
    private final ClassIdentity identity = IdentityStorage.get("Model");

    public Model(final Object instance) {
        this.instance = instance;
    }

    public int[] getXTriangles() {
        final Field field = identity.getField(identity.getIdentity("getXTriangles"));
        if (field != null) {
            try {
                return (int[]) field.get(instance);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return new int[0];
    }

    public int[] getYTriangles() {
        final Field field = identity.getField(identity.getIdentity("getYTriangles"));
        if (field != null) {
            try {
                return (int[]) field.get(instance);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return new int[0];
    }

    public int[] getZTriangles() {
        final Field field = identity.getField(identity.getIdentity("getZTriangles"));
        if (field != null) {
            try {
                return (int[]) field.get(instance);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return new int[0];
    }

    public int[] getXVertices() {
        final Field field = identity.getField(identity.getIdentity("getXVertices"));
        if (field != null) {
            try {
                return (int[]) field.get(instance);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return new int[0];
    }

    public int[] getYVertices() {
        final Field field = identity.getField(identity.getIdentity("getYVertices"));
        if (field != null) {
            try {
                return (int[]) field.get(instance);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return new int[0];
    }

    public int[] getZVertices() {
        final Field field = identity.getField(identity.getIdentity("getZVertices"));
        if (field != null) {
            try {
                return (int[]) field.get(instance);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return new int[0];
    }

}
