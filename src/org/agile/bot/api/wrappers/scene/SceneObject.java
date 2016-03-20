package org.agile.bot.api.wrappers.scene;

import org.agile.bot.Global;
import org.agile.bot.api.Game;
import org.agile.bot.api.utilities.Calculations;
import org.agile.bot.api.wrappers.Renderable;
import org.agile.bot.api.wrappers.Tile;
import org.agile.injection.wrappers.ModelCache;
import org.agile.reflection.storage.IdentityStorage;
import org.agile.reflection.storage.type.ClassIdentity;

import java.lang.reflect.Field;

/**
 * User: Francis(AgileTM)
 * Date: 18/08/13
 * Time: 7:42 PM
 * Project: Client
 * Package: org.agile.bot.api.wrappers.scene
 */
public class SceneObject {

    private final Object instance;
    private final ClassIdentity identity = IdentityStorage.get("SceneObject");

    public SceneObject(final Object instance) {
        this.instance = instance;
    }

    public int getX() {
        return (getLocalX() >> 7) + Game.getBaseX();
    }

    public int getY() {
        return (getLocalY() >> 7) + Game.getBaseY();
    }

    public int getID() {
        return (getHash() >> 14) & 0x7FFF;
    }

    public Tile getLocation() {
        return new Tile((getLocalX() >> 7) + Game.getBaseX(), (getLocalY() >> 7) + Game.getBaseY());
    }

    public double getDistance() {
        return Calculations.distance(getLocation());
    }

    public SceneModel getSceneModel() {
        return new SceneModel(ModelCache.get(getRenderable().instance), this);
    }

    public int getHash() {
        final Field field = identity.getField(identity.getIdentity("getHash"));
        if (field != null) {
            try {
                return field.getInt(instance) * identity.getIdentity("getHash").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int getLocalX() {
        final Field field = identity.getField(identity.getIdentity("getLocalX"));
        if (field != null) {
            try {
                return field.getInt(instance) * identity.getIdentity("getLocalX").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int getLocalY() {
        final Field field = identity.getField(identity.getIdentity("getLocalY"));
        if (field != null) {
            try {
                return field.getInt(instance) * identity.getIdentity("getLocalY").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int getPlane() {
        final Field field = identity.getField(identity.getIdentity("getPlane"));
        if (field != null) {
            try {
                return field.getInt(instance);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int getOrientation() {
        final Field field = identity.getField(identity.getIdentity("getOrientation"));
        if (field != null) {
            try {
                return field.getInt(instance) * identity.getIdentity("getOrientation").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public Renderable getRenderable() {
        final Field field = identity.getField(identity.getIdentity("getRenderable"));
        if (field != null) {
            try {
                return new Renderable(field.get(instance));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
