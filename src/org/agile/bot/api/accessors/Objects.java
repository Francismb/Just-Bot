package org.agile.bot.api.accessors;

import org.agile.bot.api.Game;
import org.agile.bot.api.utilities.Filter;
import org.agile.bot.api.wrappers.Tile;
import org.agile.bot.api.wrappers.scene.SceneBlock;
import org.agile.bot.api.wrappers.scene.SceneObject;
import org.agile.bot.api.wrappers.scene.SceneRegion;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Francis(AgileTM)
 * Date: 18/08/13
 * Time: 7:20 PM
 * Project: Client
 * Package: org.agile.bot.api.accessors
 */
public class Objects {

    public static SceneObject getAt(final Tile tile) {
        return get(tile.getX(), tile.getY()).get(0);
    }

    public static SceneObject getNearest(final int... ids) {
        return getNearest(new Filter<SceneObject>() {
            @Override
            public boolean accept(SceneObject sceneObject) {
                for (final int id : ids) {
                    if (sceneObject.getID() == id) return true;
                }
                return false;
            }
        });
    }

    public static SceneObject getNearest(final Filter<SceneObject> filter) {
        SceneObject current = null;
        for (final SceneObject object : get()) {
            if (current == null || object.getDistance() < current.getDistance()) current = object;
        }
        return current;
    }

    public static List<SceneObject> get() {
        final List<SceneObject> objects = new ArrayList<>();
        for (int x = 0; x < 104; x++) {
            for (int y = 0; y < 104; y++) {
                for (final SceneObject object : get(x, y)) {
                    if (object != null) {
                        objects.add(object);
                    }
                }
            }
        }
        return objects;
    }

    public static List<SceneObject> get(final int x, final int y) {
        final int plane = Game.getPlane();
        final SceneRegion region = Game.getSceneRegion();
        if (region == null) return null;
        for (final SceneBlock block : region.getBlocks()) {
            if (block != null && block.getPlane() == plane && block.getX() == x && block.getY() == y) {
                return block.getSceneObjects();
            }
        }
        return null;
    }

}
