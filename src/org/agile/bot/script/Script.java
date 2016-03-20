package org.agile.bot.script;

import org.agile.bot.api.utilities.PaintListener;

/**
 * Created with IntelliJ IDEA.
 * User: Francis
 * Date: 28/03/13
 * Time: 5:22 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Script implements PaintListener {

    public abstract boolean init();

    public abstract int run();

    public abstract void stop();

}
