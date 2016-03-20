package org.agile.bot.api.wrappers.entity.npc;

import org.agile.bot.api.wrappers.entity.CharacterEntity;
import org.agile.reflection.storage.IdentityStorage;
import org.agile.reflection.storage.type.ClassIdentity;

import java.lang.reflect.Field;

/**
 * User: Francis(AgileTM)
 * Date: 6/08/13
 * Time: 5:00 PM
 * Project: Client
 * Package: org.agile.bot.api.wrappers.entity.npc
 */
public class Npc extends CharacterEntity {

    private final Object instance;
    private final ClassIdentity identity = IdentityStorage.get("Npc");

    public Npc(final Object instance) {
        super(instance);
        this.instance = instance;
    }

    public NpcDefinition getDefinition() {
        final Field field = identity.getField(identity.getIdentity("getDefinition"));
        if (field != null) {
            try {
                return new NpcDefinition(field.get(instance));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
