package org.agile;

import org.agile.ui.MainFrame;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

/**
 * Created with IntelliJ IDEA.
 * User: Francis
 * Date: 31/03/13
 * Time: 4:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class Application {

    public static void main(final String[] args) {
        if (!Configuration.valid()) {
            System.out.println("<<< Configuration >>>");
            Configuration.installDirectories();
            Configuration.installFiles();
        }
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    try {
                        UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceGraphiteAquaLookAndFeel");
                    } catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    final MainFrame login = new MainFrame();
                    login.setVisible(true);
                }
            });
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
