package com.ccprog3.gui;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

/**
 * Custom Text Field component for custom design
 * 
 * @author Justin Ryan Uy
 */
public class CustomTextField extends JTextField {
    /**
     * Constructor for {@code CustomTextField} component
     * 
     * @param title Title of the Text Field
     */
    public CustomTextField(String title) {
        setBorder(BorderFactory.createTitledBorder(title));
    }
}
