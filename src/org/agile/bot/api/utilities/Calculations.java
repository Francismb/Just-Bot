package org.agile.bot.api.utilities;

import org.agile.bot.api.Game;
import org.agile.bot.api.accessors.Camera;
import org.agile.bot.api.accessors.Players;
import org.agile.bot.api.wrappers.Tile;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Francis
 * Date: 30/03/13
 * Time: 9:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class Calculations {

    public static final Rectangle GAMESCREEN = new Rectangle(4, 4, 512, 334);
    public static final int[] CURVESIN = new int[2048];
    public static final int[] CURVECOS = new int[2048];

    public static Point tileToScreen(final Tile tile) {
        return tileToScreen(tile.getX(), tile.getY(), Game.getPlane());
    }

    public static Point tileToScreen(final int tileX, final int tileY, final int height) {
        return worldToScreen(tileX, tileY, height);
    }

    public static Point tileToScreen(final Tile tile, final double dx, final double dy, final int height) {
        return worldToScreen((int) ((tile.getX() - Game.getBaseX() + dx) * 128), (int) ((tile.getY() - Game.getBaseY() + dy) * 128), height);
    }

    public static Point worldToScreen(final int x, final int y) {
        return worldToScreen(x, y, Game.getPlane());
    }

    public static double distance(final Tile tile) {
        return distance(Players.getLocal().getLocation(), tile);
    }

    public static double distance(final Tile t1, final Tile t2) {
        return distance(t1.getX(), t1.getY(), t2.getX(), t2.getY());
    }

    public static double distance(final int x1, final int y1, final int x2, final int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public static Point tileToMinimap(Tile tile) {
        int x = tile.getX() - Game.getBaseX();
        int y = tile.getY() - Game.getBaseY();
        return worldToMinimap((x * 4 + 2) - Players.getLocal().getLocalX() / 32, (y * 4 + 2) - Players.getLocal().getLocalY() / 32);
    }

    public static Point groundToScreen(final int x, final int y, final int plane, final int height) {
        if (x < 512 || y < 512 || x > 52224 || y > 52224) {
            return new Point(-1, -1);
        }
        final int z = tileHeight(plane, x, y) - height;
        return worldToScreen(x, z, y);
    }

    public static int tileHeight(final int plane, final int x, final int y) {
        final int[][][] groundHeights = Game.getTileHeights();
        if (groundHeights == null)
            return 0;
        int x1 = x >> 7;
        int y1 = y >> 7;
        if (x1 < 0 || y1 < 0 || x1 > 103 || y1 > 103)
            return 0;
        int x2 = x & 0x7f;
        int y2 = y & 0x7f;
        int zIndex = plane;
        if (zIndex > 3 && (Game.getTileSettings()[1][x1][y1] & 2) == 2)
            zIndex++;
        int i2 = (((-x2 + 128) * groundHeights[zIndex][x1][y1]) + (x2 * groundHeights[zIndex][x1 + 1][y1])) >> 7;
        int j2 = ((groundHeights[zIndex][x1][1 + y1] * (128 - x2)) + (groundHeights[zIndex][1 + x1][1 + y1] * x2)) >> 7;
        return ((i2 * (128 - y2)) - -(y2 * j2)) >> 7;
    }

    public static Point worldToScreen(int X, int Y, final int height) {
        if (X < 128 || Y < 128 || X > 13056 || Y > 13056) {
            return new Point(-1, -1);
        }
        int Z = tileHeight(Game.getPlane(), X, Y) - height;

        X -= Camera.getCameraX();
        Y -= Camera.getCameraY();
        Z -= Camera.getCameraZ();

        int pitch_sin = CURVESIN[Camera.getCameraPitch()];
        int pitch_cos = CURVECOS[Camera.getCameraPitch()];
        int yaw_sin = CURVESIN[Camera.getCameraYaw()];
        int yaw_cos = CURVECOS[Camera.getCameraYaw()];

        int _angle = Y * yaw_sin + X * yaw_cos >> 16;

        Y = Y * yaw_cos - X * yaw_sin >> 16;
        X = _angle;
        _angle = Z * pitch_cos - Y * pitch_sin >> 16;
        Y = Z * pitch_sin + Y * pitch_cos >> 16;
        Z = _angle;

        if (Y >= 50) {
            return new Point(256 + (X << 9) / Y, (_angle << 9) / Y + 167);
        }
        return new Point(-1, -1);
    }

    public static Point worldToMinimap(int regionX, int regionY) {
        int angle = Game.getMapScale() + Game.getCompassAngle() & 0x7FF;
        int j = regionX * regionX + regionY * regionY;
        if (j > 6400) {
            return new Point(-1, -1);
        }
        int sin = CURVESIN[angle] * 256 / (Game.getMapOffset() + 256);
        int cos = CURVECOS[angle] * 256 / (Game.getMapOffset() + 256);
        int x = regionY * sin + regionX * cos >> 16;
        int y = regionY * cos - regionX * sin >> 16;
        return new Point(644 + x, 80 - y);
    }

    public static boolean isOnscreen(final Point point) {
        return GAMESCREEN.contains(point);
    }

    public static boolean isOnscreen(final int x, final int y) {
        return GAMESCREEN.contains(new Point(x, y));
    }

    static {
        for (int i = 0; i < 2048; i++) {
            CURVESIN[i] = (int) (65536D * Math.sin((double) i * 0.0030679614999999999D));
            CURVECOS[i] = (int) (65536D * Math.cos((double) i * 0.0030679614999999999D));
        }
    }

}
