package org.agile.bot.api.utilities.input;

import org.agile.bot.Global;
import org.agile.bot.api.accessors.Npcs;
import org.agile.bot.api.utilities.Calculations;
import org.agile.bot.api.utilities.Random;
import org.agile.bot.api.utilities.Time;
import org.agile.bot.api.utilities.Timer;
import org.agile.injection.wrappers.MouseListener;
import org.agile.reflection.storage.IdentityStorage;
import org.agile.reflection.storage.type.ClassIdentity;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;

/**
 * User: Francis(AgileTM)
 * Date: 6/08/13
 * Time: 6:46 PM
 * Project: Client
 * Package: org.agile.bot.api.utilities.input
 */
public class Mouse {

    public static int getX() {
        return getMouse().getX();
    }

    public static int getY() {
        return getMouse().getY();
    }

    public static void click(boolean left) {
        pressMouse(left);
        Time.sleep(50, 100);
        releaseMouse(left);
    }

    public static void moveRandomly() {
        apply(new MouseNode(new ViewPoint() {
            final Point point = new Point(getX() + Random.nextInt(-200, 200), getY() + Random.nextInt(-200, 200));

            @Override
            public Point getNext() {
                return point;
            }

            @Override
            public boolean valid() {
                return getX() == point.x && getY() == point.y;
            }
        }));
    }

    public static boolean apply(final Point point) {
        return apply(new MouseNode(new ViewPoint() {
            @Override
            public Point getNext() {
                return point;
            }

            @Override
            public boolean valid() {
                return getX() == point.x && getY() == point.y;
            }
        }));
    }

    public static boolean apply(final Point point, final Runnable runnable) {
        return apply(new MouseNode(new ViewPoint() {
            @Override
            public Point getNext() {
                return point;
            }

            @Override
            public boolean valid() {
                return getX() == point.x && getY() == point.y;
            }
        }, runnable));
    }

    public static boolean apply(final MouseNode node) {
        final Timer timer = new Timer(Random.nextInt(4500, 5500)) {{
            reset();
        }};
        final MouseExecutor executor = new MouseExecutor();
        while (!node.getViewPoint().valid() && timer.isRunning()) {
            final Point point = node.getViewPoint().getNext();
            executor.step(point.x, point.y);
        }
        if (timer.isRunning()) {
            if (node.getRunnable() != null) node.getRunnable().run();
            return true;
        }
        return false;
    }

    public static void hopMouse(final Point point) {
        hopMouse((int) point.getX(), (int) point.getY());
    }

    public static void hopMouse(int x, int y) {
        getMouse().mouseMoved(new MouseEvent(Global.applet, MouseEvent.MOUSE_MOVED, System.currentTimeMillis(), 0, x, y, 0, false));
    }

    private static void pressMouse(boolean button) {
        getMouse().mousePressed(new MouseEvent(Global.applet, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0,
                Mouse.getMouse().getX(), Mouse.getMouse().getY(), 1, false, button ? MouseEvent.BUTTON1 : MouseEvent.BUTTON3));
    }

    private static void releaseMouse(boolean button) {
        getMouse().mouseReleased(new MouseEvent(Global.applet, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), 0,
                Mouse.getMouse().getX(), Mouse.getMouse().getY(), 1, false, button ? MouseEvent.BUTTON1 : MouseEvent.BUTTON3));

        getMouse().mouseClicked(new MouseEvent(Global.applet, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0,
                Mouse.getMouse().getX(), Mouse.getMouse().getY(), 1, false, button ? MouseEvent.BUTTON1 : MouseEvent.BUTTON3));
    }

    public static MouseListener getMouse() {
        final ClassIdentity identity = IdentityStorage.get("Client");
        final Field field = identity.getField(identity.getIdentity("getMouse"));
        if (field != null) {
            try {
                return (MouseListener) field.get(Global.clientInstance);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
