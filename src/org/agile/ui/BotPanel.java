package org.agile.ui;

import org.agile.bot.Global;
import org.agile.bot.api.Game;
import org.agile.bot.api.accessors.Objects;
import org.agile.bot.api.accessors.Widgets;
import org.agile.bot.api.utilities.input.Keyboard;
import org.agile.bot.api.utilities.input.Mouse;
import org.agile.bot.api.wrappers.*;
import org.agile.bot.api.wrappers.Component;
import org.agile.bot.api.wrappers.scene.SceneObject;
import org.agile.ui.debug.Debugger;
import org.agile.ui.images.ImageLocation;
import org.agile.ui.images.Images;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: frazb_000
 * Date: 18/01/13
 * Time: 2:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class BotPanel extends JPanel {

    public static BotPanel panel;

    public final BufferedImage game = new BufferedImage(765, 503, BufferedImage.TYPE_INT_RGB);
    public final BufferedImage buffer = new BufferedImage(765, 503, BufferedImage.TYPE_INT_RGB);

    private boolean input = true;
    public boolean vawd = false;

    public BotPanel() {
        panel = this;
        final JLabel label = new JLabel(Images.getImageIcon(ImageLocation.LogoImage));
        label.setBounds(185, 160, 400, 180);
        super.add(label);
        addMouseListener(mouseListener);
        addMouseMotionListener(motionListener);
        addKeyListener(keyListener);
    }

    public static BotPanel get() {
        return panel == null ? (new BotPanel()) : panel;
    }

    public void setInputMask(final boolean input) {
        this.input = input;
    }

    public boolean acceptInput() {
        return input;
    }

    public Graphics getBuffer() {
        buffer.getGraphics().setColor(Color.WHITE);
        for (final Debugger debugger : Debugger.debuggers) {
            if (debugger.active) {
                debugger.paint(buffer.getGraphics());
            }
        }
        if (Global.scriptManager != null) {
            if (Global.scriptManager.random != null) {
                Global.scriptManager.random.paint(buffer.getGraphics());
            } else {
                Global.scriptManager.script.paint(buffer.getGraphics());
            }
        }
        game.getGraphics().drawImage(this.buffer, 0, 0, null);
        repaint();
        return buffer.getGraphics();
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        if (Global.applet != null) {
            g.drawImage(game, 0, 0, null);
        }
    }

    private final MouseListener mouseListener = new MouseListener() {
        public void mouseClicked(final MouseEvent e) {
            if (acceptInput() && Global.applet != null) {
                try {
                    if (!hasFocus()) {
                        requestFocus();
                    }
                    Mouse.getMouse().mouseClicked(e);
                } catch (final Exception ignore) {
                }
            }
        }

        public void mouseEntered(final MouseEvent e) {
            if (acceptInput() && Global.applet != null) {
                try {
                    Mouse.getMouse().mouseEntered(e);
                } catch (final Exception ignore) {
                }
            }
        }

        public void mouseExited(final MouseEvent e) {
            if (acceptInput() && Global.applet != null) {
                try {
                    Mouse.getMouse().mouseExited(e);
                } catch (final Exception ignore) {
                }
            }
        }

        public void mousePressed(final MouseEvent e) {
            if (acceptInput() && Global.applet != null) {
                try {
                    Mouse.getMouse().mousePressed(e);
                } catch (final Exception ignore) {
                }
            }
        }

        public void mouseReleased(final MouseEvent e) {
            if (acceptInput() && Global.applet != null) {
                try {
                    Mouse.getMouse().mouseReleased(e);
                } catch (final Exception ignore) {
                }
            }
        }
    };

    private final MouseMotionListener motionListener = new MouseMotionListener() {
        @Override
        public void mouseDragged(MouseEvent e) {
            if (acceptInput() && Global.applet != null) {
                try {
                    Mouse.getMouse().mouseDragged(e);
                } catch (final Exception ignore) {
                }
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if (acceptInput() && Global.applet != null) {
                try {
                    Mouse.getMouse().mouseMoved(e);
                } catch (final Exception ignore) {
                }
            }
        }
    };

    private final KeyListener keyListener = new KeyListener() {

        @Override
        public void keyTyped(KeyEvent e) {
            if (acceptInput() && Global.applet != null) {
                try {
                    Keyboard.getKeyboard().keyTyped(e);
                } catch (final Exception ignore) {
                }
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (acceptInput() && Global.applet != null) {
                try {
                    Keyboard.getKeyboard().keyPressed(e);
                } catch (final Exception ignore) {
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (acceptInput() && Global.applet != null) {
                try {
                    Keyboard.getKeyboard().keyReleased(e);
                } catch (final Exception ignore) {
                }
            }
        }
    };
}
