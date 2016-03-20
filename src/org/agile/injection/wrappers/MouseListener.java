package org.agile.injection.wrappers;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Francis
 * Date: 30/03/13
 * Time: 9:46 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class MouseListener extends FocusListener implements java.awt.event.MouseListener, MouseMotionListener {

    private int x, y;

    public abstract void _mouseClicked(MouseEvent paramMouseEvent);

    public abstract void _mouseDragged(MouseEvent paramMouseEvent);

    public abstract void _mouseEntered(MouseEvent paramMouseEvent);

    public abstract void _mouseExited(MouseEvent paramMouseEvent);

    public abstract void _mouseMoved(MouseEvent paramMouseEvent);

    public abstract void _mousePressed(MouseEvent paramMouseEvent);

    public abstract void _mouseReleased(MouseEvent paramMouseEvent);

    @Override
    public void mouseClicked(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
        _mouseClicked(e);
        e.consume();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
        _mousePressed(e);
        e.consume();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
        _mouseReleased(e);
        e.consume();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
        _mouseEntered(e);
        e.consume();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
        _mouseExited(e);
        e.consume();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
        _mouseDragged(e);
        e.consume();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
        _mouseMoved(e);
        e.consume();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point getLocation() {
        return new Point(x, y);
    }
}
