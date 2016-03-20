package org.agile.ui.debug;


import org.agile.bot.api.accessors.Menu;

import java.awt.*;

/**
 * User: Francis(AgileTM)
 * Date: 12/08/13
 * Time: 4:37 PM
 * Project: Client
 * Package: org.agile.ui.debug
 */
public class MenuDebugger extends Debugger {

    @Override
    public void paint(Graphics graphics) {
        if (Menu.isOpen()) {
            final Rectangle bounds = Menu.getBounds();
            graphics.drawRect((int) bounds.getX(), (int) bounds.getY(), (int) bounds.getWidth(), (int) bounds.getHeight());
            graphics.drawString("Menu X : " + Menu.getMenuX(), 5, 35);
            graphics.drawString("Menu Y : " + Menu.getMenuY(), 5, 50);
            graphics.drawString("Menu Width : " + Menu.getMenuWidth(), 5, 65);
            graphics.drawString("Menu Height : " + Menu.getMenuHeight(), 5, 80);
            int indent = 80;
            for (final String action : Menu.getActions()) {
                graphics.drawString("Action : " + action + ", Option : " + Menu.getTargets().get(Menu.getActions().indexOf(action)), 5, indent += 15);
            }
        } else {
            graphics.drawString("Open The Menu To Debug", 5, 35);
            int indent = 80;
            for (final String action : Menu.getActions()) {
                graphics.drawString("Action : " + action + ", Option : " + Menu.getTargets().get(Menu.getActions().indexOf(action)), 5, indent += 15);
            }
        }
    }
}
