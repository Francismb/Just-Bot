package org.agile.bot.api.utilities.input;

/**
 * User: Francis(AgileTM)
 * Date: 15/08/13
 * Time: 9:57 AM
 * Project: Client
 * Package: org.agile.bot.api.utilities.input
 */
public class MouseNode {

    private final Runnable runnable;
    private final ViewPoint view;

    public MouseNode(final ViewPoint view, final Runnable runnable) {
        this.view = view;
        this.runnable = runnable;
    }

    public MouseNode(final ViewPoint view) {
        this(view, null);
    }

    public ViewPoint getViewPoint() {
        return view;
    }

    public Runnable getRunnable() {
        return runnable;
    }
}
