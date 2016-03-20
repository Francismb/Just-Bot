package org.agile.bot.api.accessors;

import org.agile.bot.Global;
import org.agile.bot.api.wrappers.entity.player.Player;
import org.agile.reflection.storage.IdentityStorage;
import org.agile.reflection.storage.type.ClassIdentity;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Francis(AgileTM)
 * Date: 6/08/13
 * Time: 9:48 AM
 * Project: Client
 * Package: org.agile.bot.api.accessors
 */
public class Players {

    private static ClassIdentity identity = IdentityStorage.get("Client");

    public static Player getLocal() {
        if (identity == null) identity = IdentityStorage.get("Client");
        final Field field = identity.getField(identity.getIdentity("getLocalPlayer"));
        if (field != null) {
            try {
                return new Player(field.get(Global.clientInstance));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static List<Player> getLoaded() {
        if (identity == null) identity = IdentityStorage.get("Client");
        final List<Player> players = new ArrayList<>();
        final Field field = identity.getField(identity.getIdentity("getLoadedPlayers"));
        if (field != null) {
            try {
                final Object array = field.get(Global.clientInstance);
                if (array != null) {
                    for (int index = 0; index < Array.getLength(array); index++) {
                        final Object playerData = Array.get(array, index);
                        if (playerData != null) {
                            players.add(new Player(playerData));
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return players;
    }
}
