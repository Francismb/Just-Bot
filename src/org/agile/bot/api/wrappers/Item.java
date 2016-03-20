package org.agile.bot.api.wrappers;

import org.agile.bot.api.accessors.Menu;
import org.agile.bot.api.utilities.Random;
import org.agile.bot.api.utilities.Time;
import org.agile.bot.api.utilities.input.Mouse;
import org.agile.bot.api.utilities.input.MouseNode;
import org.agile.bot.api.utilities.input.ViewPoint;

import java.awt.*;


/**
 * User: Francis(AgileTM)
 * Date: 14/08/13
 * Time: 10:31 PM
 * Project: Client
 * Package: org.agile.bot.api.wrappers
 */
public class Item {

    private final int ID;
    private final int count;
    private final int index;

    public Item(final int index, final int ID, final int count) {
        this.ID = ID;
        this.index = index;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public int getID() {
        return ID;
    }

    public int getIndex() {
        return index;
    }

    public boolean interact(final String action) {
        final Point point = getRandomPoint();
        return Mouse.apply(new MouseNode(new ViewPoint() {
            @Override
            public Point getNext() {
                return point;
            }

            @Override
            public boolean valid() {
                return Mouse.getX() == point.x && Mouse.getY() == point.y;
            }
        }, new Runnable() {
            @Override
            public void run() {
                if (Menu.isOpen()) {
                    Mouse.moveRandomly();
                }
                final int index = Menu.getActionIndex(action);
                if (index != -1) {
                    if (index == 0) {
                        Mouse.click(true);
                    } else {
                        for (int i = 0; i < 10; i++) {
                            Mouse.click(false);
                            if (Time.sleep(500, 1000, new Time.Condition() {
                                @Override
                                public boolean valid() {
                                    return Menu.isOpen();
                                }
                            })) {
                                if (Menu.interact(index)) {
                                    if (Time.sleep(400, 800, new Time.Condition() {
                                        @Override
                                        public boolean valid() {
                                            return !Menu.isOpen();
                                        }
                                    })) {
                                        Time.sleep(700, 1200);
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        ));
    }

    public Point getRandomPoint() {
        final Point point = geMidPoint();
        return new Point((int) point.getX() + (-12 + Random.nextInt(0, 24)), (int) point.getY() + (-12 + Random.nextInt(0, 24)));
    }

    public Point geMidPoint() {
        int col = (index % 4);
        int row = (index / 4);
        int x = 580 + (col * 42);
        int y = 228 + (row * 36);
        return new Point(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        if (ID != item.ID) return false;
        if (count != item.count) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = ID;
        result = 31 * result + count;
        return result;
    }
}
