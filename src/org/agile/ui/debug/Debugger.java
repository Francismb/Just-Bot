package org.agile.ui.debug;

import org.agile.bot.api.utilities.PaintListener;

import java.awt.*;

/**
 * User: Francis(AgileTM)
 * Date: 11/08/13
 * Time: 4:54 PM
 * Project: Client
 * Package: org.agile.ui.debug
 */
public abstract class Debugger implements PaintListener {

    public static Debugger[] debuggers = new Debugger[] {
            new CameraDebugger(),
            new GameDebugger(),
            new MouseDebugger(),
            new NpcDebugger(),
            new PlayerDebugger(),
            new MenuDebugger(),
            new ComponentDebugger(),
            new InventoryDebugger()
    };

    public boolean active = false;

    public abstract void paint(final Graphics graphics);
}
