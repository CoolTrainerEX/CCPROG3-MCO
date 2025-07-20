package com.ccprog3.gui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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

        GridBagLayout layout = new GridBagLayout();

        panel.setBackground(Colors.NEUTRAL.getColor());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(layout);
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

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

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;

        List<Component> invisibleComponents = new ArrayList<>();

        int count = panel.getComponentCount();

        for (int i = 0; i < count; i++) {
            Component component = panel.getComponent(i);

            if (!component.isVisible()) {
                invisibleComponents.add(component);
                component.setVisible(true);
            }
            c.insets = new Insets(i == 0 ? 0 : 5, 0, i == count - 1 ? 0 : 5, 0);
            layout.setConstraints(component, c);
        }

        pack();

        for (Component component : invisibleComponents)
            component.setVisible(false);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
