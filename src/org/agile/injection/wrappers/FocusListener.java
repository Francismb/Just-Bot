package org.agile.injection.wrappers;

import java.awt.event.FocusEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Francis
 * Date: 29/03/13
 * Time: 8:28 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class FocusListener implements java.awt.event.FocusListener {

    public abstract void _focusLost(FocusEvent event);
    public abstract void _focusGained(FocusEvent event);

    public void focusGained(final FocusEvent event) {
        _focusGained(event);
    }

    public void focusLost(final FocusEvent event) {
        _focusLost(event);
    }
}
