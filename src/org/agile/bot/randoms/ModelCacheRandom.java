package org.agile.bot.randoms;

import org.agile.bot.api.Game;
import org.agile.bot.api.utilities.PaintListener;
import org.agile.injection.wrappers.ModelCache;

import java.awt.*;

/**
 * User: Francis(AgileTM)
 * Date: 17/08/13
 * Time: 1:03 PM
 * Project: Client
 * Package: org.agile.bot.randoms
 */
public class ModelCacheRandom implements RandomEvent, PaintListener {
    @Override
    public boolean valid() {
        return Game.getGameState() != 30 && ModelCache.clearable();
    }

    @Override
    public void run() {
        ModelCache.clear();
    }

    @Override
    public void paint(Graphics graphics) {
        //graphics.setColor(new Color(135, 135, 135, 150));
        //graphics.drawRect(Calculations.GAMESCREEN.x, Calculations.GAMESCREEN.y, Calculations.GAMESCREEN.width, Calculations.GAMESCREEN.height);
    }
}
