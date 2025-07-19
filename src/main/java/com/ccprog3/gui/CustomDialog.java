package com.ccprog3.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Custom Dialog component for custom design
 * 
 * @author Justin Ryan Uy
 */
public class CustomDialog extends JDialog {
    /**
     * Constructor for {@code CustomDialog} component
     * 
     * @param owner The Frame from which the dialog is displayed
     * @param title The String to display in the dialog's title bar
     * @param panel The panel to display
     */
    public CustomDialog(JFrame owner, String title, JPanel panel) {
        super(owner, title, true);

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridLayout(0, 1, 0, 10));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setContentPane(panel);

        CustomButton button = new CustomButton("Done", null);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                remove(button);
            }
        });

        add(button);
        getRootPane().setDefaultButton(button);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
