package org.agile.bot.api.wrappers.entity.player;

import org.agile.bot.Global;
import org.agile.bot.api.wrappers.entity.CharacterEntity;
import org.agile.reflection.storage.IdentityStorage;
import org.agile.reflection.storage.type.ClassIdentity;

import java.lang.reflect.Field;

/**
 * User: Francis(AgileTM)
 * Date: 6/08/13
 * Time: 9:50 AM
 * Project: Client
 * Package: org.agile.bot.api.wrappers.entity.player
 */
public class Player extends CharacterEntity {

    private final Object instance;
    private final ClassIdentity identity = IdentityStorage.get("Player");

    public Player(final Object instance) {
        super(instance);
        this.instance = instance;
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
        return null;
    }

    public int getPrayerIcon() {
        final Field field = identity.getField(identity.getIdentity("getPrayerIcon"));
        if (field != null) {
            try {
                return field.getInt(instance) * identity.getIdentity("getPrayerIcon").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int getSkullIcon() {
        final Field field = identity.getField(identity.getIdentity("getSkullIcon"));
        if (field != null) {
            try {
                return field.getInt(instance) * identity.getIdentity("getSkullIcon").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public PlayerDefinition getDefinition() {
        final Field field = identity.getField(identity.getIdentity("getDefinition"));
        if (field != null) {
            try {
                return new PlayerDefinition(field.get(instance));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
