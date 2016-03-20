package org.agile.bot.api.accessors;

import org.agile.bot.Global;
import org.agile.reflection.storage.IdentityStorage;
import org.agile.reflection.storage.type.ClassIdentity;

import java.lang.reflect.Field;

/**
 * User: Francis(AgileTM)
 * Date: 6/08/13
 * Time: 9:25 AM
 * Project: Client
 * Package: org.agile.bot.api.accessors
 */
public enum Skills {

    ATTACK(0), DEFENSE(1), STRENGH(2), HITPOINTS(3), RANGE(4), PRAYER(5), MAGIC(6),
    COOKING(7), WOODCUTTING(8), FLETCHING(9), FISHING(10), FIREMAKING(11), CRAFTING(12),
    SMITHING(13), MINING(14), HERBLORE(15), AGILITY(16), THIEVING(17), SLAYER(18),
    FARMING(19), RUNCRAFTING(20), HUNTER(21), CONSTRUCTION(22), SUMMONING(23);

    private final int index;

    private Skills(final int index) {
        this.index = index;
    }

    public int getLevelValue() {
        final ClassIdentity identity = IdentityStorage.get("Client");
        final Field field = identity.getField(identity.getIdentity("getSkillLevelArray"));
        if (field != null) {
            try {
                return ((int[]) field.get(Global.clientInstance))[index];
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int getExperianceValue() {
        final ClassIdentity identity = IdentityStorage.get("Client");
        final Field field = identity.getField(identity.getIdentity("getSkillExpArray"));
        if (field != null) {
            try {
                return ((int[]) field.get(Global.clientInstance))[index];
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

}
