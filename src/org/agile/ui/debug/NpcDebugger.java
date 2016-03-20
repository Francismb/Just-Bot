package org.agile.ui.debug;

import org.agile.bot.api.accessors.Npcs;
import org.agile.bot.api.utilities.Calculations;
import org.agile.bot.api.wrappers.entity.npc.Npc;

import java.awt.*;

/**
 * User: Francis(AgileTM)
 * Date: 11/08/13
 * Time: 5:06 PM
 * Project: Client
 * Package: org.agile.ui.debug
 */
public class NpcDebugger extends Debugger {

    @Override
    public void paint(Graphics graphics) {
        for (final Npc npc : Npcs.get()) {
            final Point point = Calculations.tileToScreen(npc.getLocation(), 0.5, 0.5, npc.getModelHeight() / 2);
            graphics.drawString(npc.getDefinition().getName() + ":" + npc.getDefinition().getID() + ":" + npc.getAnimation(), point.x, point.y);
        }
    }

}
