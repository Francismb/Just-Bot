package org.agile.bot.api.wrappers;

import org.agile.bot.Global;
import org.agile.bot.api.Game;
import org.agile.bot.api.accessors.Widgets;
import org.agile.bot.api.utilities.generic.ComponentNode;
import org.agile.bot.api.utilities.generic.HashTableIterator;
import org.agile.reflection.storage.IdentityStorage;
import org.agile.reflection.storage.type.ClassIdentity;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Francis(AgileTM)
 * Date: 13/08/13
 * Time: 4:51 PM
 * Project: Client
 * Package: org.agile.bot.api.wrappers
 */
public class Component {

    private final Object instance;
    private final ClassIdentity identity = IdentityStorage.get("Component");

    public Component(final Object instance) {
        this.instance = instance;
    }

    public int getParentID() {
        final Field field = identity.getField(identity.getIdentity("getParentID"));
        if (field != null) {
            try {
                return (field.getInt(instance) * identity.getIdentity("getParentID").getMultiplier());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int getWidth() {
        final Field field = identity.getField(identity.getIdentity("getWidth"));
        if (field != null) {
            try {
                return field.getInt(instance) * identity.getIdentity("getWidth").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int getHeight() {
        final Field field = identity.getField(identity.getIdentity("getHeight"));
        if (field != null) {
            try {
                return field.getInt(instance) * identity.getIdentity("getHeight").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int getXRotation() {
        final Field field = identity.getField(identity.getIdentity("getXRotation"));
        if (field != null) {
            try {
                return field.getInt(instance) * identity.getIdentity("getXRotation").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int getZRotation() {
        final Field field = identity.getField(identity.getIdentity("getZRotation"));
        if (field != null) {
            try {
                return field.getInt(instance) * identity.getIdentity("getZRotation").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int getScrollX() {
        final Field field = identity.getField(identity.getIdentity("getScrollX"));
        if (field != null) {
            try {
                return field.getInt(instance) * identity.getIdentity("getScrollX").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int getScrollY() {
        final Field field = identity.getField(identity.getIdentity("getScrollY"));
        if (field != null) {
            try {
                return field.getInt(instance) * identity.getIdentity("getScrollY").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int getModelID() {
        final Field field = identity.getField(identity.getIdentity("getModelID"));
        if (field != null) {
            try {
                return field.getInt(instance) * identity.getIdentity("getModelID").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int getComponentID() {
        final Field field = identity.getField(identity.getIdentity("getComponentID"));
        if (field != null) {
            try {
                return (field.getInt(instance) * identity.getIdentity("getComponentID").getMultiplier());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int getX() {
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

    public int getMasterX() {
        try {
            final Field field = identity.loadClass().getField("masterX");
            if (field != null) {
                return field.getInt(instance);
            }
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getMasterY() {
        try {
            final Field field = identity.loadClass().getField("masterY");
            if (field != null) {
                return field.getInt(instance);
            }
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getY() {
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

    public String[] getActions() {
        final Field field = identity.getField(identity.getIdentity("getActions"));
        if (field != null) {
            try {
                return (String[]) field.get(instance);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return new String[0];
    }

    public String getComponentName() {
        final Field field = identity.getField(identity.getIdentity("getComponentName"));
        if (field != null) {
            try {
                return field.get(instance).toString();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return "Null";
    }

    public String getText() {
        final Field field = identity.getField(identity.getIdentity("getText"));
        if (field != null) {
            try {
                return field.get(instance).toString();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return "Null";
    }

    public int getStaticPosition() {
        final Field field = identity.getField(identity.getIdentity("getStaticPosition"));
        if (field != null) {
            try {
                return field.getInt(instance) * identity.getIdentity("getStaticPosition").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public Rectangle getBounds() {
        return new Rectangle(getX() + getMasterX(), getY() + getMasterY(), getWidth(), getHeight());
    }

    public int getIndex() {
        return getComponentID() & 0xFFFF;
    }

    public Component[] getChildren() {
        final Field field = identity.getField(identity.getIdentity("getChildren"));
        if (field != null) {
            try {
                final Object[] data = (Object[]) field.get(instance);
                if (data != null) {
                    final Component[] children = new Component[data.length];
                    for (int i = 0; i < data.length; i++) {
                        if (data[i] != null) {
                            children[i] = new Component(data[i]);
                        }
                    }
                    return children;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public int[] getSlotContents() {
        final Field field = identity.getField(identity.getIdentity("getSlotContents"));
        if (field != null) {
            try {
                return (int[]) field.get(instance);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return new int[0];
    }

    public int[] getSlotStacks() {
        final Field field = identity.getField(identity.getIdentity("getSlotStacks"));
        if (field != null) {
            try {
                return (int[]) field.get(instance);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return new int[0];
    }
}
