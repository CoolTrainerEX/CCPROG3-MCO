package com.ccprog3.gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
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
        setContentAreaFilled(false);
        setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        setForeground(Colors.TEXT.getColor());
        setBorder(BorderFactory.createLineBorder(Colors.BORDER.getColor()));
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(Images.PRIMARY_BACKGROUND.getImage(), 0, 0, getWidth(), getHeight(), null);
        super.paintComponent(g);
    }
}
