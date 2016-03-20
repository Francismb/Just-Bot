package org.agile.injection.wrappers;

import java.awt.event.KeyEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Francis
 * Date: 29/03/13
 * Time: 8:27 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class KeyListener extends FocusListener implements java.awt.event.KeyListener {

    public abstract void _keyTyped(KeyEvent event);

    public abstract void _keyPressed(KeyEvent event);

    public abstract void _keyReleased(KeyEvent event);

    @Override
    public void keyTyped(final KeyEvent event) {
        _keyTyped(event);
    }

    @Override
    public void keyPressed(final KeyEvent event) {
        _keyPressed(event);
    }

    @Override
    public void keyReleased(final KeyEvent event) {
        _keyReleased(event);
    }
}
