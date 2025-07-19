package com.ccprog3.gui;

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
    }
}
