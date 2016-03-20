package org.agile.bot.api.utilities.generic;

import org.agile.reflection.storage.IdentityStorage;
import org.agile.reflection.storage.type.ClassIdentity;

import java.lang.reflect.Field;

/**
 * User: Francis(AgileTM)
 * Date: 13/08/13
 * Time: 6:15 PM
 * Project: Client
 * Package: org.agile.bot.api.utilities.generic
 */
public class ComponentNode extends Node {

    private final Object instance;
    private final ClassIdentity identity = IdentityStorage.get("ComponentNode");

    public ComponentNode(Object instance) {
        super(instance);
        this.instance = instance;
    }

    public int getComponentID() {
        final Field field = identity.getField(identity.getIdentity("getUID"));
        if (field != null) {
            try {
                return field.getInt(instance) * identity.getIdentity("getUID").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }
}
