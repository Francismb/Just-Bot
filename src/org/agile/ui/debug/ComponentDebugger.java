package org.agile.ui.debug;

import org.agile.bot.api.accessors.Widgets;
import org.agile.bot.api.utilities.input.Mouse;
import org.agile.bot.api.wrappers.Component;

import java.awt.*;

/**
 * User: Francis(AgileTM)
 * Date: 13/08/13
 * Time: 11:50 PM
 * Project: Client
 * Package: org.agile.ui.debug
 */
public class ComponentDebugger extends Debugger {

    @Override
    public void paint(Graphics graphics) {
        final int mouseX = Mouse.getX();
        final int mouseY = Mouse.getY();
        final Component[][] components = Widgets.get();
        for (int i = 0; i < components.length; i++) {
            if (components[i] != null) {
                for (final Component component : components[i]) {
                    if (component != null) {
                        final Rectangle bounds = component.getBounds();
                        if (bounds.contains(mouseX, mouseY)) {
                            graphics.drawRect((int) bounds.getX(), (int) bounds.getY(), (int) bounds.getWidth(), (int) bounds.getHeight());
                            graphics.drawString("Component ID : " + i + ", " + component.getIndex(), (int) bounds.getX(), (int) bounds.getY() + 15);
                        }
                    }
                }
            }
        }
    }

}