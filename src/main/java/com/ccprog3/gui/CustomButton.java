package com.ccprog3.gui;

import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Custom Button component for custom design
 * 
 * @author Justin Ryan Uy
 */
public class CustomButton extends JButton {
    /**
     * Constructor for {@code CustomButton} component
     * 
     * @param text Text to display
     * @param l    The {@code ActionListener} to be added
     */
    public CustomButton(String text, ActionListener l) {
        super(text);
        addActionListener(l);
    }
}
