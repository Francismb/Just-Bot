package org.agile.ui.debug;

import org.agile.bot.api.accessors.Inventory;
import org.agile.bot.api.wrappers.Item;

import java.awt.*;

/**
 * User: Francis(AgileTM)
 * Date: 14/08/13
 * Time: 10:53 PM
 * Project: Client
 * Package: org.agile.ui.debug
 */
public class InventoryDebugger extends Debugger {
    @Override
    public void paint(Graphics graphics) {
        for (final Item item : Inventory.get()) {
            graphics.drawString(String.valueOf(item.getID()), (int) item.geMidPoint().getX() - 10, (int) item.geMidPoint().getY());
        }
    }
}
