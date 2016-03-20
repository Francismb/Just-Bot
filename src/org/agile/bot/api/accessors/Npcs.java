package org.agile.bot.api.accessors;

import org.agile.bot.Global;
import org.agile.bot.api.utilities.Filter;
import org.agile.bot.api.wrappers.entity.CharacterEntity;
import org.agile.bot.api.wrappers.entity.npc.Npc;
import org.agile.reflection.storage.IdentityStorage;
import org.agile.reflection.storage.type.ClassIdentity;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Francis(AgileTM)
 * Date: 6/08/13
 * Time: 5:00 PM
 * Project: Client
 * Package: org.agile.bot.api.accessors
 */
public class Npcs {

    private static ClassIdentity identity = IdentityStorage.get("Client");

    public static Npc getNearest(final int... ids) {
        return getNearest(new Filter<Npc>() {
            @Override
            public boolean accept(Npc npc) {
                for (int i : ids) {
                    if (npc.getDefinition().getID() == i) {
                        return true;
                    }
                }
                return false;
            }
        });
    }

    public static Npc getNearest(final Filter<Npc> filter) {
        double dis = 999999;
        Npc best = null;
        for (final Npc npc : get(filter)) {
            if (npc.distance() < dis) {
                best = npc;
                dis = npc.distance();
            }
        }
        return best;
    }

    public static List<Npc> get(final Filter<Npc> filter) {
        List<Npc> npcs = new ArrayList<>();
        for (final Npc npc : get()) {
            if (filter.accept(npc)) npcs.add(npc);
        }
        return npcs;
    }

    public static List<Npc> get() {
        if (identity == null) identity = IdentityStorage.get("Client");
        final List<Npc> npcs = new ArrayList<>();
        final Field field = identity.getField(identity.getIdentity("getLoadedNpcs"));
        if (field != null) {
            try {
                final Object array = field.get(Global.clientInstance);
                if (array != null) {
                    for (int index = 0; index < Array.getLength(array); index++) {
                        final Object npcData = Array.get(array, index);
                        if (npcData != null) {
                            npcs.add(new Npc(npcData));
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return npcs;
    }
}
