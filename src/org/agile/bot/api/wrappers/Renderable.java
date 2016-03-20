package org.agile.bot.api.wrappers;

import org.agile.bot.api.wrappers.model.CacheModel;
import org.agile.injection.wrappers.ModelCache;
import org.agile.reflection.storage.IdentityStorage;
import org.agile.reflection.storage.type.ClassIdentity;

import java.lang.reflect.Field;

/**
 * User: Francis(AgileTM)
 * Date: 8/08/13
 * Time: 12:05 AM
 * Project: Client
 * Package: org.agile.bot.api.wrappers
 */
public class Renderable {

    public final Object instance;
    private final ClassIdentity identity = IdentityStorage.get("Renderable");

    public Renderable(final Object instance) {
        this.instance = instance;
    }

    public int getModelHeight() {
        final Field field = identity.getField(identity.getIdentity("getModelHeight"));
        if (field != null) {
            try {
                return field.getInt(instance) * identity.getIdentity("getModelHeight").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    protected CacheModel getModel() {
        return ModelCache.get(instance);
    }

}
