package org.agile.bot.api.wrappers.entity;

import org.agile.bot.api.utilities.Calculations;
import org.agile.bot.api.utilities.Random;
import org.agile.bot.api.wrappers.model.CacheModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * User: Francis(AgileTM)
 * Date: 8/08/13
 * Time: 9:53 AM
 * Project: Client
 * Package: org.agile.bot.api.wrappers.entity
 */
public class CharacterModel {

    private final CacheModel model;
    private final CharacterEntity character;

    private final int[] trianglesX;
    private final int[] trianglesZ;
    private final int[] trianglesY;
    private int[] verticesX;
    private int[] verticesZ;
    private int[] verticesY;

    public CharacterModel(final CacheModel model, final CharacterEntity character) {
        if (model != null) {
            this.model = model;
            this.character = character;
            this.trianglesX = this.model.getTrianglesX();
            this.trianglesY = this.model.getTrianglesY();
            this.trianglesZ = this.model.getTrianglesZ();
            this.verticesX = this.model.getVerticiesX();
            this.verticesY = this.model.getVerticiesY();
            this.verticesZ = this.model.getVerticiesZ();
            final int orientation = character.getOrientation();
            if (orientation != 0) {
                int[] x_base = Arrays.copyOfRange(verticesX, 0, verticesX.length);
                int[] z_base = Arrays.copyOfRange(verticesZ, 0, verticesZ.length);
                verticesX = new int[x_base.length];
                verticesZ = new int[z_base.length];
                int theta = orientation & 0x3fff;
                int sin = Calculations.CURVESIN[theta];
                int cos = Calculations.CURVECOS[theta];
                for (int i = 0; i < x_base.length; ++i) {
                    verticesX[i] = (x_base[i] * cos + z_base[i] * sin >> 15) >> 1;
                    verticesZ[i] = (z_base[i] * cos - x_base[i] * sin >> 15) >> 1;
                }
            }
        } else {
            throw new RuntimeException("Couldn't Obtain Character Model From Cache");
        }
    }

    public int[] getXTriangles() {
        return trianglesX;
    }

    public int[] getZTriangles() {
        return trianglesZ;
    }

    public int[] getYTriangles() {
        return trianglesY;
    }

    public int[] getXVerticies() {
        return verticesX;
    }

    public int[] getYVerticies() {
        return verticesY;
    }

    public int[] getZVerticies() {
        return verticesZ;
    }

    public List<Polygon> getTriangles() {
        final int locX = character.getLocalX();
        final int locY = character.getLocalY();
        final List<Polygon> polygons = new LinkedList<>();
        for (int i = 0; i < trianglesX.length; ++i) {
            final Point p1 = Calculations.worldToScreen(locX + verticesX[trianglesX[i]], locY + verticesZ[trianglesX[i]], -verticesY[trianglesX[i]]);
            final Point p2 = Calculations.worldToScreen(locX + verticesX[trianglesY[i]], locY + verticesZ[trianglesY[i]], -verticesY[trianglesY[i]]);
            final Point p3 = Calculations.worldToScreen(locX + verticesX[trianglesZ[i]], locY + verticesZ[trianglesZ[i]], -verticesY[trianglesZ[i]]);
            if (p1.x >= 0 && p2.x >= 0 && p3.x >= 0 && Calculations.isOnscreen(p1) && Calculations.isOnscreen(p2) && Calculations.isOnscreen(p3)) {
                polygons.add(new Polygon(new int[]{p1.x, p2.x, p3.x}, new int[]{p1.y, p2.y, p3.y}, 3));
            }
        }
        return polygons;
    }

    public List<Point> getPoints() {
        final List<Point> points = new ArrayList<>();
        for (final Polygon polygon : getTriangles()) {
            for (int i = 0; i < polygon.npoints; i++) {
                points.add(new Point(polygon.xpoints[i], polygon.ypoints[i]));
            }
        }
        return points;
    }

    public Point getRandomPoint() {
        List<Polygon> triangles = getTriangles();
        for (int i = 0; i < triangles.size(); i++) {
            Polygon p = triangles.get(Random.nextInt(0, triangles.size()));
            Point point = new Point(p.xpoints[Random.nextInt(0, p.xpoints.length)], p.ypoints[Random.nextInt(0, p.ypoints.length)]);
            if (Calculations.isOnscreen(point)) {
                return point;
            }
        }
        return null;
    }
}
