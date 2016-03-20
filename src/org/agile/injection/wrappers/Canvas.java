package org.agile.injection.wrappers;

import org.agile.ui.BotPanel;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Francis
 * Date: 26/03/13
 * Time: 5:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class Canvas extends java.awt.Canvas {

    @Override
    public Graphics getGraphics() {
        return BotPanel.get().getBuffer();
    }

}
