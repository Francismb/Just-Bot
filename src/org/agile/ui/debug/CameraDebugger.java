package org.agile.ui.debug;

import org.agile.bot.api.accessors.Camera;

import java.awt.*;

/**
 * User: Francis(AgileTM)
 * Date: 11/08/13
 * Time: 4:58 PM
 * Project: Client
 * Package: org.agile.ui.debug
 */
public class CameraDebugger extends Debugger {

    @Override
    public void paint(Graphics graphics) {
        graphics.drawString("Camera X : " + Camera.getCameraX(), 5, 35);
        graphics.drawString("Camera Y : " + Camera.getCameraY(), 5, 50);
        graphics.drawString("Camera Z : " + Camera.getCameraZ(), 5, 65);
        graphics.drawString("Camera Pitch : " + Camera.getCameraPitch(), 5, 80);
        graphics.drawString("Camera Yaw : " + Camera.getCameraYaw(), 5, 95);
    }

}
