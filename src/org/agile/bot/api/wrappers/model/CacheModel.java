package org.agile.bot.api.wrappers.model;

import java.util.Arrays;

/**
 * User: Francis(AgileTM)
 * Date: 9/08/13
 * Time: 3:05 PM
 * Project: Client
 * Package: org.agile.bot.api.wrappers.model
 */
public class CacheModel {

    private int[] trianglesX;
    private int[] trianglesZ;
    private int[] trianglesY;
    private int[] verticiesX;
    private int[] verticiesZ;
    private int[] verticiesY;

    public void assign(final int[] trianglesX, final int[] trianglesY, final int[] trianglesZ, final int[] verticiesX, final int[] verticiesY, final int[] verticiesZ) {
        this.trianglesX = Arrays.copyOf(trianglesX, trianglesX.length);
        this.trianglesY = Arrays.copyOf(trianglesY, trianglesY.length);
        this.trianglesZ = Arrays.copyOf(trianglesX, trianglesZ.length);
        this.verticiesX = Arrays.copyOf(verticiesX, verticiesX.length);
        this.verticiesY = Arrays.copyOf(verticiesY, verticiesY.length);
        this.verticiesZ = Arrays.copyOf(verticiesZ, verticiesZ.length);
    }

    public int[] getTrianglesX() {
        return trianglesX;
    }

    public int[] getTrianglesZ() {
        return trianglesZ;
    }

    public int[] getTrianglesY() {
        return trianglesY;
    }

    public int[] getVerticiesX() {
        return verticiesX;
    }

    public int[] getVerticiesZ() {
        return verticiesZ;
    }

    public int[] getVerticiesY() {
        return verticiesY;
    }
}
