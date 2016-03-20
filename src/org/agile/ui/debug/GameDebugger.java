package org.agile.ui.debug;

import org.agile.bot.api.Game;
import org.agile.bot.api.accessors.Players;

import java.awt.*;

/**
 * User: Francis(AgileTM)
 * Date: 11/08/13
 * Time: 5:04 PM
 * Project: Client
 * Package: org.agile.ui.debug
 */
public class GameDebugger extends Debugger {

    @Override
    public void paint(Graphics graphics) {
        graphics.drawString("Game State : " + Game.getGameState(), 5, 35);
        graphics.drawString("LoginFrame Index : " + Game.getLoginIndex(), 5, 50);
        graphics.drawString("Map Base : " + Game.getBaseX() + ", " + Game.getBaseY(), 5, 65);
        graphics.drawString("Cycle : " + Game.getCycle(), 5, 80);
    }

}
