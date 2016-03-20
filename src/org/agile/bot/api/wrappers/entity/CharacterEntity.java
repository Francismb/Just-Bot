package org.agile.bot.api.wrappers.entity;

import org.agile.bot.api.Game;
import org.agile.bot.api.accessors.Menu;
import org.agile.bot.api.utilities.Calculations;
import org.agile.bot.api.utilities.Time;
import org.agile.bot.api.utilities.input.Mouse;
import org.agile.bot.api.utilities.input.MouseNode;
import org.agile.bot.api.utilities.input.ViewPoint;
import org.agile.bot.api.wrappers.Interactable;
import org.agile.bot.api.wrappers.Renderable;
import org.agile.bot.api.wrappers.Tile;
import org.agile.reflection.storage.IdentityStorage;
import org.agile.reflection.storage.type.ClassIdentity;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Francis(AgileTM)
 * Date: 6/08/13
 * Time: 9:49 AM
 * Project: Client
 * Package: org.agile.bot.api.wrappers.entity
 */
public class CharacterEntity extends Renderable implements Interactable {

    private final Object instance;
    private final ClassIdentity identity = IdentityStorage.get("CharacterEntity");

    public CharacterEntity(final Object instance) {
        super(instance);
        this.instance = instance;
    }

    public int getAnimation() {
        final Field field = identity.getField(identity.getIdentity("getAnimation"));
        if (field != null) {
            try {
                return field.getInt(instance) * identity.getIdentity("getAnimation").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public boolean isAnimating() {
        final Field field = identity.getField(identity.getIdentity("isAnimating"));
        if (field != null) {
            try {
                return field.getBoolean(instance);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public String getSpeech() {
        final Field field = identity.getField(identity.getIdentity("getSpokenText"));
        if (field != null) {
            try {
                return field.get(instance).toString();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public int getLocalX() {
        final Field field = identity.getField(identity.getIdentity("getX"));
        if (field != null) {
            try {
                return field.getInt(instance) * identity.getIdentity("getX").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int getLocalY() {
        final Field field = identity.getField(identity.getIdentity("getY"));
        if (field != null) {
            try {
                return field.getInt(instance) * identity.getIdentity("getY").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int getOrientation() {
        final Field field = identity.getField(identity.getIdentity("getOrientation"));
        if (field != null) {
            try {
                return (field.getInt(instance) * identity.getIdentity("getOrientation").getMultiplier()) & 0x3fff;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int getCycle() {
        final Field field = identity.getField(identity.getIdentity("getCycle"));
        if (field != null) {
            try {
                return (field.getInt(instance) * identity.getIdentity("getCycle").getMultiplier());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public boolean isInCombat() {
        return getCycle() > Game.getCycle();
    }

    public Tile getLocation() {
        return new Tile((getLocalX() >> 7) + Game.getBaseX(), (getLocalY() >> 7) + Game.getBaseY());
    }

    public CharacterModel getCharacterModel() {
        return new CharacterModel(getModel(), this);
    }

    public boolean isWalking() {
        return getWalkingQueue().size() > 0;
    }

    public List<Tile> getWalkingQueue() {
        final Field xField = identity.getField(identity.getIdentity("getQueueX"));
        final Field yField = identity.getField(identity.getIdentity("getQueueY"));
        final List<Tile> queue = new ArrayList<>();
        if (xField != null && yField != null) {
            try {
                final int[] queueX = (int[]) xField.get(instance);
                final int[] queueY = (int[]) yField.get(instance);
                for (int index = 0; index < queueX.length; index++) {
                    queue.add(new Tile(queueX[index] + (Game.getBaseX() >> 7), queueY[index] + (Game.getBaseY() >> 7)));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return queue;
    }

    public double distance() {
        return Calculations.distance(getLocation());
    }

    @Override
    public boolean interact(String action) {
        return interact("NULL", action);
    }

    @Override
    public boolean interact(final String option, final String action) {
        return Mouse.apply(new MouseNode(new ViewPoint() {
            @Override
            public Point getNext() {
                return getCharacterModel().getRandomPoint();
            }

            @Override
            public boolean valid() {
                for (final Point point : getCharacterModel().getPoints()) {
                    if (Mouse.getX() == point.x && Mouse.getY() == point.y) {
                        return true;
                    }
                }
                return false;
            }
        }, new Runnable() {
            @Override
            public void run() {
                final int index = option.equals("NULL") ? Menu.getActionIndex(action) : Menu.getSlotIndex(option, action);
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
                                Menu.interact(index);
                                return;
                            }
                        }
                    }
                }
            }
        }
        ));
    }

}
