package org.agile.bot.api.wrappers.scene;

import org.agile.bot.api.utilities.*;
import org.agile.bot.api.wrappers.entity.CharacterEntity;
import org.agile.bot.api.wrappers.model.CacheModel;

import java.awt.*;
import java.awt.List;
import java.util.*;
import java.util.Random;

/**
 * User: Francis(AgileTM)
 * Date: 18/08/13
 * Time: 9:43 PM
 * Project: Client
 * Package: org.agile.bot.api.wrappers.scene
 */
public class SceneModel {

    private final CacheModel model;
    private final SceneObject object;

    private int[] trianglesX;
    private int[] trianglesZ;
    private int[] trianglesY;
    private int[] verticesX;
    private int[] verticesZ;
    private int[] verticesY;

    public SceneModel(final CacheModel model, final SceneObject object) {
        if (model != null) {
            this.model = model;
            this.object = object;
            this.trianglesX = this.model.getTrianglesX();
            this.trianglesY = this.model.getTrianglesY();
            this.trianglesZ = this.model.getTrianglesZ();
            this.verticesX = this.model.getVerticiesX();
            this.verticesY = this.model.getVerticiesY();
            this.verticesZ = this.model.getVerticiesZ();
        } else {
            throw new NullPointerException("Model Is Null");
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

    public java.util.List<Polygon> getTriangles() {
        final int locX = object.getX();
        final int locY = object.getY();
        final java.util.List<Polygon> polygons = new LinkedList<>();
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

    public java.util.List<Point> getPoints() {
        System.out.println("Creating points...");
        final java.util.List<Point> points = new ArrayList<>();
        for (final Polygon polygon : getTriangles()) {
            for (int i = 0; i < polygon.npoints; i++) {
                System.out.println("addingpoint");
                points.add(new Point(polygon.xpoints[i], polygon.ypoints[i]));
            }
        }
        System.out.println("returning points");
        return points;
    }

    public Point getRandomPoint() {
        java.util.List<Polygon> triangles = getTriangles();
        for (int i = 0; i < triangles.size(); i++) {
            Polygon p = triangles.get(org.agile.bot.api.utilities.Random.nextInt(0, triangles.size()));
            Point point = new Point(p.xpoints[org.agile.bot.api.utilities.Random.nextInt(0, p.xpoints.length)], p.ypoints[org.agile.bot.api.utilities.Random.nextInt(0, p.ypoints.length)]);
            if (Calculations.isOnscreen(point)) {
                return point;
            }
        }
        return null;
    }


}
