package org.agile.reflection.storage.type;

/**
 * Created with IntelliJ IDEA.
 * User: Francis
 * Date: 25/03/13
 * Time: 8:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class FieldIdentity {

    public FieldIdentity(final String name, final String fieldName, final String owner, final int multiplier) {
        this.name = name;
        this.fieldName = fieldName;
        this.owner = owner;
        this.multiplier = multiplier;
    }

    public FieldIdentity(final String name, final String fieldName, final String owner) {
        this(name, fieldName, owner, -1);
    }

    private final String name;
    private final String fieldName;
    private final String owner;
    private final int multiplier;

    public String getFieldName() {
        return this.fieldName;
    }

    public int getMultiplier() {
        return this.multiplier;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }
}
