package org.agile.ui;

import org.agile.bot.Global;
import org.agile.bot.script.Script;
import org.agile.bot.script.ScriptClassLoader;
import org.agile.bot.script.ScriptDefinition;
import org.agile.bot.script.ScriptManager;
import org.agile.ui.images.ImageLocation;
import org.agile.ui.images.Images;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ScriptSelector extends JFrame {

    public ScriptSelector() {
        super("Script Selector");
        setResizable(false);
        initComponents();
    }

    private JTable table1;
    private JScrollPane scrollPane1;
    private SelectorModel selector;

    private JTextField searchTextField;
    private JButton reloadButton;
    private JButton startButton;

    private void searchKeyPressed(KeyEvent e) {

    }

    private void reloadActionPreformed(ActionEvent e) {
        selector.loadScripts();
    }

    private void startAction(ActionEvent e) {
        int selected = table1.getSelectedRow();
        ScriptDefinition def = selector.getDefinitionAt(selected);
        if (def != null) {
            final ClassLoader cl = new ScriptClassLoader(def.getSource());
            final Script script;
            try {
                script = cl.loadClass(def.getClassName()).asSubclass(Script.class).newInstance();
            } catch (final Exception ex) {
                ex.printStackTrace();
                return;
            }
            MainFrame.submitStatus("Running : " + def.getName() + " - V" + def.getVersion());
            new Thread(Global.scriptManager = new ScriptManager(script)).start();
            setVisible(false);
            dispose();
        } else {
            System.out.println("Select A Script Row Before Starting A Script");
        }
    }

    private void initComponents() {
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        reloadButton = new JButton();
        startButton = new JButton();
        searchTextField = new JTextField();

        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        {
            selector = new SelectorModel(table1);
            table1.getTableHeader().setReorderingAllowed(false);
            table1.getTableHeader().setResizingAllowed(false);
            table1.setModel(selector);
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(0, 0, 620, 270);

        searchTextField.setText("Search");
        searchTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                searchKeyPressed(e);
            }
        });
        searchTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchTextField.getText().equals("Search")) {
                    searchTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchTextField.getText().length() <= 0) {
                    searchTextField.setText("Search");
                }
            }
        });
        contentPane.add(searchTextField);
        searchTextField.setBounds(1, 271, 260, searchTextField.getPreferredSize().height);
        reloadButton.setText("Reload Scripts");
        reloadButton.setIcon(Images.getImageIcon(ImageLocation.RestartImage));
        reloadButton.setRolloverIcon(Images.getImageIcon(ImageLocation.RestartHoverImage));
        reloadButton.setPressedIcon(Images.getImageIcon(ImageLocation.RestartHoverImage));
        reloadButton.setBorderPainted(false);
        reloadButton.setContentAreaFilled(false);
        reloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reloadActionPreformed(e);
            }
        });
        contentPane.add(reloadButton);
        reloadButton.setBounds(new Rectangle(new Point(505, 270), reloadButton.getPreferredSize()));
        startButton.setText("Start Script");
        startButton.setIcon(Images.getImageIcon(ImageLocation.PlayImage));
        startButton.setRolloverIcon(Images.getImageIcon(ImageLocation.PlayHoverImage));
        startButton.setPressedIcon(Images.getImageIcon(ImageLocation.PlayHoverImage));
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startAction(e);
            }
        });
        contentPane.add(startButton);
        startButton.setBounds(new Rectangle(new Point(415, 272), startButton.getPreferredSize()));
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
        pack();
        setLocationRelativeTo(getOwner());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}
