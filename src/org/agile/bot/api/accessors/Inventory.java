package org.agile.bot.api.accessors;

import org.agile.bot.api.utilities.Filter;
import org.agile.bot.api.wrappers.Component;
import org.agile.bot.api.wrappers.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Francis(AgileTM)
 * Date: 14/08/13
 * Time: 10:19 PM
 * Project: Client
 * Package: org.agile.bot.api.accessors
 */
public class Inventory {

    private static final int INVENTORY_ID = 149;

    public static boolean contains(final int... ids) {
        return get(ids).size() > 0;
    }

    public static int getCount() {
        final List<Item> items = get();
        return items != null ? items.size() : -1;
    }

    public static int getCount(final int... ids) {
        final List<Item> items = get(ids);
        return items != null ? items.size() : -1;
    }

    public static int getCount(final Filter<Item> filter) {
        final List<Item> items = get(filter);
        return items != null ? items.size() : -1;
    }

    public static List<Item> get(final int... ids) {
        return get(new Filter<Item>() {
            @Override
            public boolean accept(Item item) {
                for (int i : ids) {
                    if (item.getID() == i) {
                        return true;
                    }
                }
                return false;
            }
        });
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
        final List<Item> items = new ArrayList<>();
        final Component component = Widgets.get(149, 0);
        if (component == null) return items;
        final int[] ids = component.getSlotContents();
        final int[] stacks = component.getSlotStacks();
        for (int index = 0; index < ids.length; index++) {
            if (ids[index] != 0) {
                items.add(new Item(index, ids[index], stacks[index]));
            }
        }
        return items;
    }
}
