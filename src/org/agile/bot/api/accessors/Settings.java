package org.agile.bot.api.accessors;

import org.agile.bot.Global;
import org.agile.reflection.storage.IdentityStorage;
import org.agile.reflection.storage.type.ClassIdentity;

import java.lang.reflect.Field;

/**
 * User: Francis(AgileTM)
 * Date: 6/08/13
 * Time: 6:37 PM
 * Project: Client
 * Package: org.agile.bot.api.accessors
 */
public class Settings {

    private static ClassIdentity identity = IdentityStorage.get("Client");

    public static int get(final int index) {
        return get()[index];
    }

    public static int[] get() {
        final Field field = identity.getField(identity.getIdentity("getSettings"));
        if (field != null) {
            try {
                return ((int[]) field.get(Global.clientInstance));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return new int[0];
    }

}
