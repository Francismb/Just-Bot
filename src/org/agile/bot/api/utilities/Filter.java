package org.agile.bot.api.utilities;

/**
 * User: Francis(AgileTM)
 * Date: 6/08/13
 * Time: 5:53 PM
 * Project: Client
 * Package: org.agile.bot.api.utilities
 */
public interface Filter<F> {
    public boolean accept(F f);
}
