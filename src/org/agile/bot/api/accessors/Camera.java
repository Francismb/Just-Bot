package org.agile.bot.api.accessors;

import org.agile.bot.Global;
import org.agile.reflection.storage.IdentityStorage;
import org.agile.reflection.storage.type.ClassIdentity;

import java.lang.reflect.Field;

/**
 * User: Francis(AgileTM)
 * Date: 6/08/13
 * Time: 9:24 AM
 * Project: Client
 * Package: org.agile.bot.api.accessors
 */
public class Camera {

    public static int getCameraX() {
        final ClassIdentity identity = IdentityStorage.get("Client");
        final Field field = identity.getField(identity.getIdentity("getCameraX"));
        if (field != null) {
            try {
                return field.getInt(Global.clientInstance) * identity.getIdentity("getCameraX").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static int getCameraY() {
        final ClassIdentity identity = IdentityStorage.get("Client");
        final Field field = identity.getField(identity.getIdentity("getCameraY"));
        if (field != null) {
            try {
                return field.getInt(Global.clientInstance) * identity.getIdentity("getCameraY").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static int getCameraZ() {
        final ClassIdentity identity = IdentityStorage.get("Client");
        final Field field = identity.getField(identity.getIdentity("getCameraZ"));
        if (field != null) {
            try {
                return field.getInt(Global.clientInstance) * identity.getIdentity("getCameraZ").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static int getCameraYaw() {
        final ClassIdentity identity = IdentityStorage.get("Client");
        final Field field = identity.getField(identity.getIdentity("getYaw"));
        if (field != null) {
            try {
                return field.getInt(Global.clientInstance) * identity.getIdentity("getYaw").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static int getCameraPitch() {
        final ClassIdentity identity = IdentityStorage.get("Client");
        final Field field = identity.getField(identity.getIdentity("getPitch"));
        if (field != null) {
            try {
                return field.getInt(Global.clientInstance) * identity.getIdentity("getPitch").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

}
