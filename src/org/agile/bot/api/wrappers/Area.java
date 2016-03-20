package org.agile.bot.api.wrappers;

import java.awt.*;

/**
 * User: Francis(AgileTM)
 * Date: 12/08/13
 * Time: 5:00 PM
 * Project: Client
 * Package: org.agile.bot.api.wrappers
 */
public class Area {

    private final Polygon area = new Polygon();

    public Area(final int x1, final int x2, final int y1, final int y2) {
        area.addPoint(x1 < x2 ? x1 : x2, y1 < y2 ? y1 : y2);
        area.addPoint(x1 < x2 ? x2 : x1, y1 < y2 ? y2 : y1);
    }

    public Area(final Tile t1, final Tile t2) {
        this(t1.getX(), t2.getX(), t1.getY(), t2.getY());
    }

    public boolean contains(final Tile t) {
        return area.contains(t.getX(), t.getY());
    }

    public void addTile(final Tile... t) {
        for (final Tile t1 : t) {
            area.addPoint(t1.getX(), t1.getY());
        }
    }

    public Dimension getDimension() {
        return area.getBounds().getSize();
    }

    public int getX() {
        return (int) area.getBounds().getX();
    }

    public int getY() {
        return (int) area.getBounds().getY();
    }

    public Tile getNearestTile(final Tile base) {
        Tile closest = null;
        double dist = 9999;
        for (int i = 0; i < area.npoints - 1; i++) {
            double nD = Math.sqrt(Math.pow(area.xpoints[i], 2) + Math.pow(area.ypoints[i], 2));
            if (nD < dist) {
                closest = new Tile(area.xpoints[i], area.ypoints[i]);
                dist = nD;
            }
        }
        return closest;
    }

}
