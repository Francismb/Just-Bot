package org.agile.ui;

import org.agile.bot.Global;
import org.agile.bot.api.Game;
import org.agile.loader.BotInitializer;
import org.agile.ui.debug.*;
import org.agile.ui.images.ImageLocation;
import org.agile.ui.images.Images;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class MainFrame extends JFrame {

    private JPanel panel2;
    private JButton settingButton;
    private JButton restartButton;
    private JButton stopButton;
    private JButton pauseButton;
    private JButton runButton;
    private JButton inputButton;
    private JPanel panel3;
    private JPopupMenu menu;
    private JButton menuButton;
    private JRadioButtonMenuItem clearSetting;
    private JRadioButtonMenuItem mouseSetting;
    private JRadioButtonMenuItem gameSetting;
    private JRadioButtonMenuItem npcSetting;
    private JRadioButtonMenuItem playersSetting;
    private JRadioButtonMenuItem objectsSetting;
    private JRadioButtonMenuItem inventorySetting;
    private JRadioButtonMenuItem groundSetting;
    private JRadioButtonMenuItem modelSetting;
    private JRadioButtonMenuItem widgetSetting;
    private JRadioButtonMenuItem settingsSetting;
    private JRadioButtonMenuItem menuSetting;
    private static JLabel label3;

    public MainFrame() {
        super("AgileBot");
        initComponents();
        setVisible(true);
        new Thread(new BotInitializer()).start();
    }

    public static void submitStatus(final String string) {
        label3.setText(string);
    }

    private void restartActionPreformed() {
        BotPanel.panel = new BotPanel();
        new Thread(new BotInitializer()).start();
    }

    private void initComponents() {
        panel2 = new JPanel();
        restartButton = new JButton();
        settingButton = new JButton();
        stopButton = new JButton();
        pauseButton = new JButton();
        runButton = new JButton();
        inputButton = new JButton();
        panel3 = new JPanel();
        label3 = new JLabel();
        menu = new JPopupMenu();
        menuButton = new JButton();
        clearSetting = new JRadioButtonMenuItem();
        mouseSetting = new JRadioButtonMenuItem();
        gameSetting = new JRadioButtonMenuItem();
        npcSetting = new JRadioButtonMenuItem();
        playersSetting = new JRadioButtonMenuItem();
        objectsSetting = new JRadioButtonMenuItem();
        inventorySetting = new JRadioButtonMenuItem();
        groundSetting = new JRadioButtonMenuItem();
        modelSetting = new JRadioButtonMenuItem();
        widgetSetting = new JRadioButtonMenuItem();
        settingsSetting = new JRadioButtonMenuItem();
        menuSetting = new JRadioButtonMenuItem();
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        {
            BotPanel.get().setLayout(null);
            BotPanel.get().setBorder(new EtchedBorder());
        }
        contentPane.add(BotPanel.get());
        BotPanel.get().setBounds(0, 25, 765, 503);
        {
            panel2.setLayout(null);

            restartButton.setIcon(Images.getImageIcon(ImageLocation.RestartImage));
            restartButton.setRolloverIcon(Images.getImageIcon(ImageLocation.RestartHoverImage));
            restartButton.setPressedIcon(Images.getImageIcon(ImageLocation.RestartHoverImage));
            restartButton.setBorderPainted(false);
            restartButton.setContentAreaFilled(false);
            restartButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    restartActionPreformed();
                }
            });
            panel2.add(restartButton);
            restartButton.setBounds(new Rectangle(new Point(28, 0), new Dimension(30, 26)));

            stopButton.setIcon(Images.getImageIcon(ImageLocation.StopImage));
            stopButton.setRolloverIcon(Images.getImageIcon(ImageLocation.StopHoverImage));
            stopButton.setPressedIcon(Images.getImageIcon(ImageLocation.StopHoverImage));
            stopButton.setBorderPainted(false);
            stopButton.setContentAreaFilled(false);
            stopButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (Global.scriptManager != null) {
                        submitStatus("Stopping Script...");
                        Global.scriptManager.stop();
                        Global.scriptManager = null;
                        submitStatus("Stopped Script");
                    }
                }
            });
            panel2.add(stopButton);
            stopButton.setBounds(new Rectangle(new Point(710, 0), new Dimension(30, 26)));

            pauseButton.setIcon(Images.getImageIcon(ImageLocation.PauseImage));
            pauseButton.setRolloverIcon(Images.getImageIcon(ImageLocation.PauseHoverImage));
            pauseButton.setPressedIcon(Images.getImageIcon(ImageLocation.PauseHoverImage));
            pauseButton.setBorderPainted(false);
            pauseButton.setContentAreaFilled(false);
            pauseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    BotPanel.get().vawd = BotPanel.get().vawd ? false : true;
                    if (Global.scriptManager != null) {
                        Global.scriptManager.pause();
                        submitStatus("Paused Script");
                    }
                }
            });
            panel2.add(pauseButton);
            pauseButton.setBounds(new Rectangle(new Point(685, 0), new Dimension(30, 26)));

            runButton.setIcon(Images.getImageIcon(ImageLocation.PlayImage));
            runButton.setRolloverIcon(Images.getImageIcon(ImageLocation.PlayHoverImage));
            runButton.setPressedIcon(Images.getImageIcon(ImageLocation.PlayHoverImage));
            runButton.setBorderPainted(false);
            runButton.setContentAreaFilled(false);
            runButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (Global.scriptManager != null) {
                        Global.scriptManager.play();
                    } else {
                        final ScriptSelector selector = new ScriptSelector();
                        selector.setVisible(true);
                    }
                }
            });
            panel2.add(runButton);
            runButton.setBounds(new Rectangle(new Point(660, 0), new Dimension(30, 26)));
            inputButton.setIcon(Images.getImageIcon(ImageLocation.KeyboardImage));
            inputButton.setRolloverIcon(Images.getImageIcon(ImageLocation.KeyboardHoverImage));
            inputButton.setPressedIcon(Images.getImageIcon(ImageLocation.KeyboardHoverImage));
            inputButton.setBorderPainted(false);
            inputButton.setContentAreaFilled(false);
            inputButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (BotPanel.get().acceptInput()) {
                        inputButton.setIcon(Images.getImageIcon(ImageLocation.CloseImage));
                        inputButton.setRolloverIcon(Images.getImageIcon(ImageLocation.CloseHoverImage));
                        inputButton.setPressedIcon(Images.getImageIcon(ImageLocation.CloseHoverImage));
                        BotPanel.get().setInputMask(false);
                    } else {
                        inputButton.setIcon(Images.getImageIcon(ImageLocation.KeyboardImage));
                        inputButton.setRolloverIcon(Images.getImageIcon(ImageLocation.KeyboardHoverImage));
                        inputButton.setPressedIcon(Images.getImageIcon(ImageLocation.KeyboardHoverImage));
                        BotPanel.get().setInputMask(true);
                    }
                }
            });
            panel2.add(inputButton);
            inputButton.setBounds(new Rectangle(new Point(735, 0), new Dimension(30, 26)));
            {
                panel3.setBorder(new EtchedBorder());
                panel3.setLayout(null);
                label3.setText("Status");
                panel3.add(label3);
                label3.setBounds(5, 0, 400, 25);
                {
                    Dimension preferredSize = new Dimension();
                    for (int i = 0; i < panel3.getComponentCount(); i++) {
                        Rectangle bounds = panel3.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel3.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel3.setMinimumSize(preferredSize);
                    panel3.setPreferredSize(preferredSize);
                }
            }
            panel2.add(panel3);
            panel3.setBounds(58, 0, 400, 26);
        }
        {
            menuButton.setIcon(Images.getImageIcon(ImageLocation.SettingImage));
            menuButton.setRolloverIcon(Images.getImageIcon(ImageLocation.SettingHoverImage));
            menuButton.setPressedIcon(Images.getImageIcon(ImageLocation.SettingHoverImage));
            menuButton.setBorderPainted(false);
            menuButton.setContentAreaFilled(false);
            menuButton.setBounds(new Rectangle(new Point(0, 0), new Dimension(30, 26)));
            menuButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    menu.show(menuButton, menuButton.getBounds().x, menuButton.getBounds().y + menuButton.getBounds().height);
                }
            });
            panel2.add(menuButton);
            {
                {
                    clearSetting.setBorderPainted(false);
                    clearSetting.setContentAreaFilled(false);
                    clearSetting.setText("Clear");
                    clearSetting.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for (final Debugger debugger : Debugger.debuggers) {
                                debugger.active = false;
                            }
                            clearSetting.setSelected(false);
                            mouseSetting.setSelected(false);
                            gameSetting.setSelected(false);
                            npcSetting.setSelected(false);
                            playersSetting.setSelected(false);
                            objectsSetting.setSelected(false);
                            inventorySetting.setSelected(false);
                            groundSetting.setSelected(false);
                            modelSetting.setSelected(false);
                            widgetSetting.setSelected(false);
                            settingsSetting.setSelected(false);
                            menuSetting.setSelected(false);
                        }
                    });
                    menu.add(clearSetting);
                }
                {
                    mouseSetting.setBorderPainted(false);
                    mouseSetting.setContentAreaFilled(false);
                    mouseSetting.setText("Mouse");
                    mouseSetting.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for (final Debugger debugger : Debugger.debuggers) {
                                if (debugger instanceof MouseDebugger) {
                                    ((MouseDebugger) debugger).active = mouseSetting.isSelected();
                                }
                            }
                        }
                    });
                    menu.add(mouseSetting);
                }
                {
                    gameSetting.setBorderPainted(false);
                    gameSetting.setContentAreaFilled(false);
                    gameSetting.setText("Game");
                    gameSetting.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for (final Debugger debugger : Debugger.debuggers) {
                                if (debugger instanceof GameDebugger) {
                                    ((GameDebugger) debugger).active = gameSetting.isSelected();
                                }
                            }
                        }
                    });
                    menu.add(gameSetting);
                }
                {
                    menuSetting.setBorderPainted(false);
                    menuSetting.setContentAreaFilled(false);
                    menuSetting.setText("Menu");
                    menuSetting.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for (final Debugger debugger : Debugger.debuggers) {
                                if (debugger instanceof MenuDebugger) {
                                    ((MenuDebugger) debugger).active = menuSetting.isSelected();
                                }
                            }
                        }
                    });
                    menu.add(menuSetting);
                }
                {
                    npcSetting.setBorderPainted(false);
                    npcSetting.setContentAreaFilled(false);
                    npcSetting.setText("Npcs");
                    npcSetting.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for (final Debugger debugger : Debugger.debuggers) {
                                if (debugger instanceof NpcDebugger) {
                                    ((NpcDebugger) debugger).active = npcSetting.isSelected();
                                }
                            }
                        }
                    });
                    menu.add(npcSetting);
                }
                {
                    playersSetting.setBorderPainted(false);
                    playersSetting.setContentAreaFilled(false);
                    playersSetting.setText("Players");
                    playersSetting.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for (final Debugger debugger : Debugger.debuggers) {
                                if (debugger instanceof PlayerDebugger) {
                                    ((PlayerDebugger) debugger).active = playersSetting.isSelected();
                                }
                            }
                        }
                    });
                    menu.add(playersSetting);
                }
                {
                    objectsSetting.setBorderPainted(false);
                    objectsSetting.setContentAreaFilled(false);
                    objectsSetting.setText("Objects");
                    objectsSetting.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                        }
                    });
                    menu.add(objectsSetting);
                }
                {
                    inventorySetting.setBorderPainted(false);
                    inventorySetting.setContentAreaFilled(false);
                    inventorySetting.setText("Inventory");
                    inventorySetting.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for (final Debugger debugger : Debugger.debuggers) {
                                if (debugger instanceof InventoryDebugger) {
                                    ((InventoryDebugger) debugger).active = inventorySetting.isSelected();
                                }
                            }
                        }
                    });
                    menu.add(inventorySetting);
                }
                {
                    groundSetting.setBorderPainted(false);
                    groundSetting.setContentAreaFilled(false);
                    groundSetting.setText("GroundItems");
                    groundSetting.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                        }
                    });
                    menu.add(groundSetting);
                }
                {
                    widgetSetting.setBorderPainted(false);
                    widgetSetting.setContentAreaFilled(false);
                    widgetSetting.setText("Widgets");
                    widgetSetting.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for (final Debugger debugger : Debugger.debuggers) {
                                if (debugger instanceof ComponentDebugger) {
                                    ((ComponentDebugger) debugger).active = widgetSetting.isSelected();
                                }
                            }
                        }
                    });
                    menu.add(widgetSetting);
                }
                {
                    settingsSetting.setBorderPainted(false);
                    settingsSetting.setContentAreaFilled(false);
                    settingsSetting.setText("Settings");
                    settingsSetting.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                        }
                    });
                    menu.add(settingsSetting);
                }
            }

        }
        contentPane.add(panel2);
        panel2.setBounds(0, 0, 765, 25);
        {
            Dimension preferredSize = new Dimension();
            for (int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        setResizable(false);
        pack();
        setLocationRelativeTo(getOwner());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}

