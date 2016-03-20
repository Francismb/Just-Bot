package org.agile.bot.api.utilities.input;

import org.agile.bot.Global;
import org.agile.injection.wrappers.KeyListener;
import org.agile.reflection.storage.IdentityStorage;
import org.agile.reflection.storage.type.ClassIdentity;

import java.lang.reflect.Field;

/**
 * User: Francis(AgileTM)
 * Date: 6/08/13
 * Time: 6:46 PM
 * Project: Client
 * Package: org.agile.bot.api.utilities.input
 */
public class Keyboard {

    public static KeyListener getKeyboard() {
        final ClassIdentity identity = IdentityStorage.get("Client");
        final Field field = identity.getField(identity.getIdentity("getKeyboard"));
        if (field != null) {
            try {
                return (KeyListener) field.get(Global.clientInstance);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
