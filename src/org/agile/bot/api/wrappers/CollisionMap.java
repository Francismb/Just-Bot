package org.agile.bot.api.wrappers;

import org.agile.bot.Global;
import org.agile.reflection.storage.IdentityStorage;
import org.agile.reflection.storage.type.ClassIdentity;

import java.lang.reflect.Field;

/**
 * User: Francis(AgileTM)
 * Date: 6/08/13
 * Time: 6:48 PM
 * Project: Client
 * Package: org.agile.bot.api.wrappers.landscape
 */
public class CollisionMap {

    private final Object instance;
    private final ClassIdentity identity = IdentityStorage.get("CollisionMap");

    public CollisionMap(final Object instance) {
        this.instance = instance;
    }

    public int[][] getFlags() {
        final Field field = identity.getField(identity.getIdentity("getFlags"));
        if (field != null) {
            try {
                return (int[][]) field.get(Global.clientInstance);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return new int[0][0];
    }
}
