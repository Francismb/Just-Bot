package org.agile.ui.images;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Francis(AgileTM)
 * Date: 15/04/13
 * Time: 8:43 PM
 */
public class Images {

    public static ImageIcon getImageIcon(final ImageLocation location) {
        final Image image = getImage(location);
        if (image != null) {
            return new ImageIcon(image);
        }
        return null;
    }

    public static Image getImage(final ImageLocation location) {
        try {
            final File file = new File(location.getLocation());
            final Image image = ImageIO.read(file);
            if (image != null) {
                return image;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
