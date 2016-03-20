package org.agile.bot.api.accessors;

import org.agile.bot.Global;
import org.agile.bot.api.wrappers.Component;
import org.agile.reflection.storage.IdentityStorage;
import org.agile.reflection.storage.type.ClassIdentity;

import java.lang.reflect.Field;

/**
 * User: Francis(AgileTM)
 * Date: 6/08/13
 * Time: 9:03 PM
 * Project: Client
 * Package: org.agile.bot.api.accessors
 */
public class Widgets {

    private static ClassIdentity identity = IdentityStorage.get("Client");


    public static Component[] get(final int group) {
        final Component[][] components = get();
        if (group < 0 || group > components.length) return null;
        return components[group];
    }

    public static Component get(final int group, final int index) {
        final Component[][] components = get();
        if (group < 0 || group > components.length) return null;
        if (index < 0 || index > components[group].length) return null;
        return get()[group][index];
    }

    public static Component[][] get() {
        if (identity == null) identity = IdentityStorage.get("Client");
        final Field field = identity.getField(identity.getIdentity("getComponents"));
        if (field != null) {
            try {
                final Object[][] data = (Object[][]) field.get(Global.clientInstance);
                final Component[][] components = new Component[data.length][];
                for (int i = 0; i < data.length; i++) {
                    if (data[i] != null) {
                        components[i] = new Component[data[i].length];
                        for (int j = 0; j < data[i].length; j++) {
                            if (data[i][j] != null) {
                                components[i][j] = new Component(data[i][j]);
                            }
                        }
                    }
                }
                return components;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return new Component[0][0];
    }

}
