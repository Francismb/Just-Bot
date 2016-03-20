package org.agile.reflection.storage;

import org.agile.reflection.storage.type.ClassIdentity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Francis
 * Date: 25/03/13
 * Time: 8:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class IdentityStorage {

    private static List<ClassIdentity> identities = new ArrayList<>();

    public static void addIdentity(final ClassIdentity identity) {
        identities.add(identity);
    }

    public static ClassIdentity get(final String name) {
        for (final ClassIdentity identity : identities) {
            if (name.equals(identity.getName())) {
                return identity;
            }
        }
        return null;
    }

    public static int getSize() {
        return identities.size();
    }

    public static ClassIdentity[] toArray() {
        return identities.toArray(new ClassIdentity[identities.size()]);
    }

}
