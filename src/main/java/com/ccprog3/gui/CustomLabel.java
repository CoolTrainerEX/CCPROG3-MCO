package com.ccprog3.gui;

import java.awt.Font;

import javax.swing.JLabel;

/**
 * Custom Label component for custom design
 * 
 * @author Justin Ryan Uy
 */
public class CustomLabel extends JLabel {
    /**
     * Constructor for {@code CustomLabel} component
     * 
     * @param text  The text to be displayed by the label
     * @param style The style constant for the Font
     */
    public CustomLabel(String text, int style) {
        super(text);
        setFont(new Font("Comic Sans MS", style, 16));
        setForeground(Colors.TEXT.getColor());
    }
}
