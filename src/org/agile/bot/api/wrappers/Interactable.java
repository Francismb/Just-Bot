package org.agile.bot.api.wrappers;

/**
 * User: Francis(AgileTM)
 * Date: 12/08/13
 * Time: 10:27 AM
 * Project: Client
 * Package: org.agile.bot.api.wrappers
 */
public interface Interactable {

    public boolean interact(final String action);
    public boolean interact(final String name, final String action);

}
