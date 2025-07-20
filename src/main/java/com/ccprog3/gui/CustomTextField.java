package com.ccprog3.gui;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

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
        TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Colors.BORDER.getColor()),
                title);

        setFont(new Font("Courier New", Font.BOLD, 16));
        setForeground(Colors.TEXT.getColor());
        border.setTitleFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        border.setTitleColor(Colors.BORDER.getColor());
        setBorder(border);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(Images.PRIMARY_BACKGROUND.getImage(), 0, 0, getWidth(), getHeight(), null);
        super.paintComponent(g);
    }
}
