package org.agile.bot.api.wrappers;

import org.agile.bot.api.Game;
import org.agile.bot.api.utilities.Calculations;
import org.agile.bot.api.utilities.input.Mouse;

import java.awt.*;

/**
 * User: Francis(AgileTM)
 * Date: 6/08/13
 * Time: 10:24 AM
 * Project: Client
 * Package: org.agile.bot.api.wrappers
 */
public class Tile {

    private final int x;
    private final int y;

    public Tile(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public Point toScreen() {
        return Calculations.tileToScreen(x, y, Game.getPlane());
    }

    public boolean isVisible() {
        return Calculations.isOnscreen(toScreen());
    }

    public boolean interactMap() {
        Mouse.apply(Calculations.tileToMinimap(this), new Runnable() {
            @Override
            public void run() {
                Mouse.click(true);
            }
        });
        return true;
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }
}
