package org.agile.bot.api.utilities.generic;

import org.agile.reflection.storage.IdentityStorage;
import org.agile.reflection.storage.type.ClassIdentity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Francis(AgileTM)
 * Date: 13/08/13
 * Time: 5:47 PM
 * Project: Client
 * Package: org.agile.bot.api.utilities.generic
 */
public class HashTable {

    private final Object instance;
    private final ClassIdentity identity = IdentityStorage.get("HashTable");

    public HashTable(final Object instance) {
        this.instance = instance;
    }

    public List<Node> getBuckets() {
        final List<Node> buckets = new ArrayList<>();
        final Field field = identity.getField(identity.getIdentity("getBuckets"));
        if (field != null) {
            try {
                for (final Object data : (Object[]) field.get(instance)) {
                    buckets.add(new Node(data));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return buckets;
    }

}
