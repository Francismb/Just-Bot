package org.agile.bot.api.wrappers.scene;

import org.agile.reflection.storage.IdentityStorage;
import org.agile.reflection.storage.type.ClassIdentity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Francis(AgileTM)
 * Date: 18/08/13
 * Time: 7:33 PM
 * Project: Client
 * Package: org.agile.bot.api.wrappers.scene
 */
public class SceneBlock {

    private final int x;
    private final int y;
    private final int plane;

    private final Object instance;
    private final ClassIdentity identity = IdentityStorage.get("SceneBlock");

    public SceneBlock(final Object instance, final int plane, final int x, final int y) {
        this.instance = instance;
        this.plane = plane;
        this.x = x;
        this.y = y;
    }

    public List<SceneObject> getSceneObjects() {
        final List<SceneObject> objects = new ArrayList<>();
        final Field field = identity.getField(identity.getIdentity("getSceneObjects"));
        if (field != null) {
            try {
                final Object[] data = (Object[]) field.get(instance);
                for (final Object object : data) {
                    if (object != null) {
                        objects.add(new SceneObject(object));
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return objects;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPlane() {
        return plane;
    }

}
