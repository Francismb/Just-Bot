package org.agile.ui.debug;

import org.agile.bot.api.accessors.Players;
import org.agile.bot.api.utilities.Calculations;
import org.agile.bot.api.wrappers.entity.CharacterModel;
import org.agile.bot.api.wrappers.entity.player.Player;

import java.awt.*;

/**
 * User: Francis(AgileTM)
 * Date: 11/08/13
 * Time: 5:13 PM
 * Project: Client
 * Package: org.agile.ui.debug
 */
public class PlayerDebugger extends Debugger {

    @Override
    public void paint(Graphics graphics) {
        graphics.drawString("Local Player", 5, 35);
        graphics.drawString("Location : " + Players.getLocal().getLocation().toString(), 5, 50);
        graphics.drawString("Animation : " + Players.getLocal().getAnimation(), 5, 65);
        for (final Player player : Players.getLoaded()) {
            final Point point = Calculations.tileToScreen(player.getLocation(), 0.5, 0.5, player.getModelHeight() / 2);
            graphics.drawString(player.getName() + ":" + player.getLocation().toString() + ":" + player.getAnimation(), point.x, point.y);
        }
    }

}
