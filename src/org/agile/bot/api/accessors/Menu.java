package org.agile.bot.api.accessors;

import org.agile.bot.Global;
import org.agile.bot.api.utilities.Random;
import org.agile.bot.api.utilities.input.Mouse;
import org.agile.reflection.storage.IdentityStorage;
import org.agile.reflection.storage.type.ClassIdentity;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Francis(AgileTM)
 * Date: 12/08/13
 * Time: 3:59 PM
 * Project: Client
 * Package: org.agile.bot.api.accessors
 */
public class Menu {

    private static ClassIdentity identity = IdentityStorage.get("Client");

    public static int getActionIndex(final String action) {
        final List<String> actions = getActions();
        for (final String string : actions) {
            if (string.toLowerCase().equals(action.toLowerCase())) {
                return actions.indexOf(string);
            }
        }
        return -1;
    }

    public static int getTargetIndex(final String target) {
        final List<String> actions = getTargets();
        for (final String string : actions) {
            if (string.toLowerCase().equals(target.toLowerCase())) {
                return actions.indexOf(string);
            }
        }
        return -1;
    }

    public static int getSlotIndex(final String option, final String action) {
        final List<String> actions = getActions();
        final List<String> options = getTargets();
        for (final String string : actions) {
            if (string.toLowerCase().equals(action.toLowerCase())) {
                for (final String string1 : options) {
                    if (string1.toLowerCase().equals(option.toLowerCase())) {
                        return actions.indexOf(string);
                    }
                }
            }
        }
        return -1;
    }

    public static Rectangle getBounds() {
        return new Rectangle(getMenuX(), getMenuY(), getMenuWidth(), getMenuHeight());
    }

    public static Point getPoint(int index) {
        Rectangle bounds = getBounds();
        Point menu = new Point(bounds.x + 4, bounds.y + 4);
        return new Point(menu.x + Random.nextInt(4, bounds.width - 4), menu.y + Random.nextInt(23, 30) + 15 * index);
    }

    public static boolean interact(final String action) {
        return interact(getActionIndex(action));
    }

    public static boolean interact(final String option, final String action) {
        return interact(getSlotIndex(option, action));
    }

    public static boolean interact(final int index) {
        if (index != -1) {
            final Point point = getPoint(index);
            Mouse.apply(point, new Runnable() {
                @Override
                public void run() {
                    Mouse.click(true);
                }
            });
            return true;
        }
        return false;
    }

    public static int getMenuX() {
        if (identity == null) identity = IdentityStorage.get("Client");
        final Field field = identity.getField(identity.getIdentity("getMenuX"));
        if (field != null) {
            try {
                return field.getInt(Global.clientInstance) * identity.getIdentity("getMenuX").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static int getMenuY() {
        if (identity == null) identity = IdentityStorage.get("Client");
        final Field field = identity.getField(identity.getIdentity("getMenuY"));
        if (field != null) {
            try {
                return field.getInt(Global.clientInstance) * identity.getIdentity("getMenuY").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static int getMenuWidth() {
        if (identity == null) identity = IdentityStorage.get("Client");
        final Field field = identity.getField(identity.getIdentity("getMenuWidth"));
        if (field != null) {
            try {
                return field.getInt(Global.clientInstance) * identity.getIdentity("getMenuWidth").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static int getMenuHeight() {
        if (identity == null) identity = IdentityStorage.get("Client");
        final Field field = identity.getField(identity.getIdentity("getMenuHeight"));
        if (field != null) {
            try {
                return field.getInt(Global.clientInstance) * identity.getIdentity("getMenuHeight").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static int getMenuOptionCount() {
        if (identity == null) identity = IdentityStorage.get("Client");
        final Field field = identity.getField(identity.getIdentity("getMenuOptionCount"));
        if (field != null) {
            try {
                return field.getInt(Global.clientInstance) * identity.getIdentity("getMenuOptionCount").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static boolean isOpen() {
        if (identity == null) identity = IdentityStorage.get("Client");
        final Field field = identity.getField(identity.getIdentity("isMenuOpen"));
        if (field != null) {
            try {
                return field.getBoolean(Global.clientInstance);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static List<String> getActions() {
        final List<String> actions = new ArrayList<>();
        final Field field = identity.getField(identity.getIdentity("getMenuActions"));
        if (field != null) {
            try {
                final String[] data = (String[]) field.get(Global.clientInstance);
                for (int index = getMenuOptionCount() - 1; index >= 0; index--) {
                    if (data[index] != null) {
                        actions.add(data[index]);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return actions;
    }

    public static List<String> getTargets() {
        final List<String> options = new ArrayList<>();
        final Field field = identity.getField(identity.getIdentity("getMenuOptions"));
        if (field != null) {
            try {
                final String[] data = (String[]) field.get(Global.clientInstance);
                for (int index = getMenuOptionCount() - 1; index >= 0; index--) {
                    if (data[index] != null) {
                        options.add(data[index]);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return options;
    }
}
