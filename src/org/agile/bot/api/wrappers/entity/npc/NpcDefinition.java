package org.agile.bot.api.wrappers.entity.npc;

import org.agile.reflection.storage.IdentityStorage;
import org.agile.reflection.storage.type.ClassIdentity;

import java.lang.reflect.Field;

/**
 * User: Francis(AgileTM)
 * Date: 6/08/13
 * Time: 5:01 PM
 * Project: Client
 * Package: org.agile.bot.api.wrappers.entity.npc
 */
public class NpcDefinition {

    private final Object instance;
    private final ClassIdentity identity = IdentityStorage.get("NpcDefinition");

    public NpcDefinition(final Object instance) {
        this.instance = instance;
    }

    public int getLevel() {
        final Field field = identity.getField(identity.getIdentity("getLevel"));
        if (field != null) {
            try {
                return field.getInt(instance) * identity.getIdentity("getLevel").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int getID() {
        final Field field = identity.getField(identity.getIdentity("getID"));
        if (field != null) {
            try {
                try {
                    return field.getInt(instance) * identity.getIdentity("getID").getMultiplier();
                } catch (final NullPointerException ignore) {}
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public String getName() {
        final Field field = identity.getField(identity.getIdentity("getName"));
        if (field != null) {
            try {
                return field.get(instance).toString();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return "Null";
    }

    public String[] getActions() {
        final Field field = identity.getField(identity.getIdentity("getName"));
        if (field != null) {
            try {
                return (String[]) field.get(instance);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return new String[0];
    }

}
