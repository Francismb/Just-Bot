package org.agile.bot.api;

import org.agile.bot.Global;
import org.agile.bot.api.utilities.generic.ComponentNode;
import org.agile.bot.api.utilities.generic.HashTable;
import org.agile.bot.api.wrappers.scene.SceneRegion;
import org.agile.injection.wrappers.KeyListener;
import org.agile.injection.wrappers.MouseListener;
import org.agile.reflection.storage.IdentityStorage;
import org.agile.reflection.storage.type.ClassIdentity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Francis
 * Date: 28/03/13
 * Time: 8:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class Game {

    private static ClassIdentity identity = IdentityStorage.get("Client");

    public static int getGameState() {
        if (identity == null) identity = IdentityStorage.get("Client");
        final Field field = identity.getField(identity.getIdentity("getGameState"));
        if (field != null) {
            try {
                return field.getInt(Global.clientInstance) * identity.getIdentity("getGameState").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static int getLoginIndex() {
        if (identity == null) identity = IdentityStorage.get("Client");
        final Field field = identity.getField(identity.getIdentity("getLoginIndex"));
        if (field != null) {
            try {
                return field.getInt(Global.clientInstance) * identity.getIdentity("getLoginIndex").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static int getPlane() {
        if (identity == null) identity = IdentityStorage.get("Client");
        final Field field = identity.getField(identity.getIdentity("getPlane"));
        if (field != null) {
            try {
                return field.getInt(Global.clientInstance) * identity.getIdentity("getPlane").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static int getBaseX() {
        if (identity == null) identity = IdentityStorage.get("Client");
        final Field field = identity.getField(identity.getIdentity("getBaseX"));
        if (field != null) {
            try {
                return field.getInt(Global.clientInstance) * identity.getIdentity("getBaseX").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static int getBaseY() {
        if (identity == null) identity = IdentityStorage.get("Client");
        final Field field = identity.getField(identity.getIdentity("getBaseY"));
        if (field != null) {
            try {
                return field.getInt(Global.clientInstance) * identity.getIdentity("getBaseY").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static int[][][] getTileHeights() {
        if (identity == null) identity = IdentityStorage.get("Client");
        final Field field = identity.getField(identity.getIdentity("getTileHeights"));
        if (field != null) {
            try {
                return (int[][][]) field.get(Global.clientInstance);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return new int[0][0][0];
    }

    public static byte[][][] getTileSettings() {
        if (identity == null) identity = IdentityStorage.get("Client");
        final Field field = identity.getField(identity.getIdentity("getTileBytes"));
        if (field != null) {
            try {
                return (byte[][][]) field.get(Global.clientInstance);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return new byte[0][0][0];
    }

    public static int getCompassAngle() {
        if (identity == null) identity = IdentityStorage.get("Client");
        final Field field = identity.getField(identity.getIdentity("getCompassAngle"));
        if (field != null) {
            try {
                return field.getInt(Global.clientInstance) * identity.getIdentity("getCompassAngle").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static int getMapScale() {
        if (identity == null) identity = IdentityStorage.get("Client");
        final Field field = identity.getField(identity.getIdentity("getMapScale"));
        if (field != null) {
            try {
                return field.getInt(Global.clientInstance) * identity.getIdentity("getMapScale").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static int getMapOffset() {
        if (identity == null) identity = IdentityStorage.get("Client");
        final Field field = identity.getField(identity.getIdentity("getMapOffset"));
        if (field != null) {
            try {
                return field.getInt(Global.clientInstance) * identity.getIdentity("getMapOffset").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static int getCycle() {
        if (identity == null) identity = IdentityStorage.get("Client");
        final Field field = identity.getField(identity.getIdentity("getCycle"));
        if (field != null) {
            try {
                return field.getInt(Global.clientInstance) * identity.getIdentity("getCycle").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static int[] getComponentPositionsX() {
        if (identity == null) identity = IdentityStorage.get("Client");
        final Field field = identity.getField(identity.getIdentity("getComponentPositionX"));
        if (field != null) {
            try {
                return (int[]) field.get(Global.clientInstance);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return new int[0];
    }

    public static int[] getComponentPositionsY() {
        if (identity == null) identity = IdentityStorage.get("Client");
        final Field field = identity.getField(identity.getIdentity("getComponentPositionY"));
        if (field != null) {
            try {
                return (int[]) field.get(Global.clientInstance);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return new int[0];
    }

    public static HashTable getComponentNodes() {
        if (identity == null) identity = IdentityStorage.get("Client");
        final Field field = identity.getField(identity.getIdentity("getComponentNodes"));
        if (field != null) {
            try {
                return new HashTable(field.get(Global.clientInstance));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static SceneRegion getSceneRegion() {
        if (identity == null) identity = IdentityStorage.get("Client");
        final Field field = identity.getField(identity.getIdentity("getSceneRegion"));
        if (field != null) {
            try {
                return new SceneRegion(field.get(Global.clientInstance));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
