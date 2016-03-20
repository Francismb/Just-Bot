package org.agile.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginFrame extends JFrame {

    public LoginFrame() {
        initComponents();
    }

    private void loginActionPreformed(ActionEvent e) {
        this.dispose();
    }

    private void registerActionPreformed(ActionEvent e) {
        this.dispose();
    }

    private void initComponents() {
        label1 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();
        textField1 = new JTextField();
        passwordField1 = new JPasswordField();
        label2 = new JLabel();
        label3 = new JLabel();

        setTitle("Login");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        label1.setText("<HTML><img src=\"https://dl.dropboxusercontent.com/u/28468007/images/small_logo.png\"/></HTML>");
        contentPane.add(label1);
        label1.setBounds(20, 0, 205, 80);

        button1.setText("Login");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginActionPreformed(e);
            }
        });
        contentPane.add(button1);
        button1.setBounds(155, 150, 80, button1.getPreferredSize().height);

        button2.setText("Register");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerActionPreformed(e);
            }
        });
        contentPane.add(button2);
        button2.setBounds(5, 150, 80, button2.getPreferredSize().height);
        contentPane.add(textField1);
        textField1.setBounds(62, 85, 170, textField1.getPreferredSize().height);
        contentPane.add(passwordField1);
        passwordField1.setBounds(62, 120, 170, passwordField1.getPreferredSize().height);

        label2.setText("Password");
        contentPane.add(label2);
        label2.setBounds(5, 120, 55, 20);

        label3.setText("Username");
        contentPane.add(label3);
        label3.setBounds(5, 85, label3.getPreferredSize().width, 20);

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
        setSize(255, 215);
        setLocationRelativeTo(getOwner());
    }

    private JLabel label1;
    private JButton button1;
    private JButton button2;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JLabel label2;
    private JLabel label3;
}
