package org.agile.bot.api.wrappers.entity.player;

import org.agile.reflection.storage.IdentityStorage;
import org.agile.reflection.storage.type.ClassIdentity;

import java.lang.reflect.Field;

/**
 * User: Francis(AgileTM)
 * Date: 6/08/13
 * Time: 10:18 AM
 * Project: Client
 * Package: org.agile.bot.api.wrappers.entity.player
 */
public class PlayerDefinition {

    private final Object instance;
    private final ClassIdentity identity = IdentityStorage.get("PlayerDefinition");

    public PlayerDefinition(final Object instance) {
        this.instance = instance;
    }

    public int[] getEquipment() {
        final Field field = identity.getField(identity.getIdentity("getEquipment"));
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
