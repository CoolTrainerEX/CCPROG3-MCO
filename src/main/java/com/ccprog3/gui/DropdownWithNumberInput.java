package com.ccprog3.gui;

import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * Combo Box with number Text Field
 * 
 * @param <E> The type of the elements of the Combo Box
 * @author Justin Ryan Uy
 */
public class DropdownWithNumberInput<E> extends JPanel {
    /**
     * Combo Box component
     */
    private CustomComboBox<E> comboBox;

    /**
     * Text field component
     */
    private CustomTextField textField;

    /**
     * Constructor for the component
     * 
     * @param items An array of objects to insert into the Combo Box
     * @param title Title of the Text Field
     */
    public DropdownWithNumberInput(E[] items, String title) {
        setLayout(new GridLayout(0, 2, 10, 0));
        add(comboBox = new CustomComboBox<>(items));
        add(textField = new CustomTextField(title));
    }

    /**
     * Gets the Combo Box component
     * 
     * @return Combo Box component
     */
    public CustomComboBox<E> getComboBox() {
        return comboBox;
    }

    /**
     * Gets the Text Field component
     * 
     * @return Text Field component
     */
    public CustomTextField getTextField() {
        return textField;
    }
}
