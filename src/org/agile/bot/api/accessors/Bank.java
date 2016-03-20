package org.agile.bot.api.accessors;

import org.agile.bot.api.utilities.Filter;
import org.agile.bot.api.wrappers.Component;
import org.agile.bot.api.wrappers.Item;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Francis(AgileTM)
 * Date: 15/08/13
 * Time: 10:15 PM
 * Project: Client
 * Package: org.agile.bot.api.accessors
 */
public class Bank {

    public static final int BANK_EXIT = 103;
    public static final int INTERFACE_ID = 12;
    public static final int ITEM_PANE_ID = 89;
    public static final int CAPACITY = 400;

    public static boolean isOpen() {
        Component pane = Widgets.get(INTERFACE_ID, ITEM_PANE_ID);
        if (pane == null) return false;
        for (int i = 0; i < pane.getSlotContents().length; i++) {
            if (pane.getSlotContents()[i] == 0 && pane.getSlotStacks()[i] > 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean deposit(final int id, final int amount) {
        return deposit(get(new Filter<Item>() {
            @Override
            public boolean accept(Item item) {
                return item.getID() == id;
            }
        }).get(0), amount);
    }

    public static boolean deposit(final Item item, final int amount) {
        if (!isOpen()) return false;
        if (!contains(item)) return false;
        switch (amount) {
            case 0:
                break;
            case 1:
                break;
            default:
                break;
        }
        return true;
    }

    public static boolean contains(final Item i) {
        for (final Item item : get()) {
            if (item.equals(i)) return true;
        }
        return false;
    }

    public static Point getMidPoint(final int index) {
        final Component component = Widgets.get(12, 89);
        if (component == null) return null;
        final Rectangle bounds = component.getBounds();
        return new Point((index % 8) * 47 + bounds.x + 57, (index / 8) * 37 + bounds.y + 75);
    }

    public static Item get(final int index) {
        return get().get(index);
    }

    public static List<Item> get(final Filter<Item> filter) {
        final List<Item> items = new ArrayList<>();
        for (final Item item : get()) {
            if (filter.accept(item)) {
                items.add(item);
            }
        }
        return items;
    }

    public static List<Item> get() {
        if (!isOpen()) return null;
        final List<Item> items = new ArrayList<>();
        final Component pane = Widgets.get(INTERFACE_ID, ITEM_PANE_ID);
        for (int index = 0; index < CAPACITY; index++) {
            if (pane.getSlotContents()[index] != 0) {
                items.add(new Item(index, pane.getSlotContents()[index], pane.getSlotStacks()[index]));
            }
        }
        return items;
    }
}
