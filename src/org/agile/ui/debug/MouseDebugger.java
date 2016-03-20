package org.agile.ui.debug;

import org.agile.bot.api.accessors.Camera;
import org.agile.bot.api.utilities.input.Mouse;

import java.awt.*;

/**
 * User: Francis(AgileTM)
 * Date: 11/08/13
 * Time: 5:01 PM
 * Project: Client
 * Package: org.agile.ui.debug
 */
public class MouseDebugger extends Debugger {

    @Override
    public void paint(Graphics graphics) {
        final int x = Mouse.getX();
        final int y = Mouse.getY();
        graphics.drawString("Mouse X : " + x, 5, 35);
        graphics.drawString("Mouse Y : " + y, 5, 50);
        graphics.drawLine(x + 10, y, x - 10, y);
        graphics.drawLine(x, y + 10, x, y - 10);
    }

}
