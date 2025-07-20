package com.ccprog3.gui;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;

/**
 * Custom Combo Box component for custom design
 * 
 * @param <E> The type of the elements of this Combo Box
 * @author Justin Ryan Uy
 */
public class CustomComboBox<E> extends JComboBox<E> {
    /**
     * Constructor for {@code CustomComboBox} component
     * 
     * @param items An array of objects to insert into the Combo Box
     */
    public CustomComboBox(E[] items) {
        super(items);
        setFont(new Font("Courier New", Font.PLAIN, 16));
        setForeground(Colors.TEXT.getColor());
        setBackground(Colors.PRIMARY.getColor());
        setBorder(BorderFactory.createLineBorder(Colors.BORDER.getColor()));
    }
}
