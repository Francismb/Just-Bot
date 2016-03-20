package org.agile.bot.api.utilities.generic;

import org.agile.reflection.storage.IdentityStorage;
import org.agile.reflection.storage.type.ClassIdentity;

import java.lang.reflect.Field;

/**
 * User: Francis(AgileTM)
 * Date: 13/08/13
 * Time: 5:43 PM
 * Project: Client
 * Package: org.agile.bot.api.utilities.generic
 */
public class Node {

    private final Object instance;
    private final ClassIdentity identity = IdentityStorage.get("Node");

    public Node(final Object instance) {
        this.instance = instance;
    }

    public int getID() {
        final Field field = identity.getField(identity.getIdentity("getID"));
        if (field != null) {
            try {
                return field.getInt(instance) * identity.getIdentity("getID").getMultiplier();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public Node getNext() {
        final Field field = identity.getField(identity.getIdentity("getNext"));
        if (field != null) {
            try {
                return new Node(field.get(instance));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Node getPrevious() {
        final Field field = identity.getField(identity.getIdentity("getPrevious"));
        if (field != null) {
            try {
                return new Node(field.get(instance));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
