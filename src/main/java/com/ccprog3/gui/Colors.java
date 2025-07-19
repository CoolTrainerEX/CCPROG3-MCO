package com.ccprog3.gui;

import java.awt.Color;

/**
 * GUI Color scheme
 * 
 * @author Justin Ryan Uy
 */
public enum Colors {
    /**
     * Neutral color
     */
    NEUTRAL(new Color(0xe6ccb2)),

    /**
     * Primary color
     */
    PRIMARY(new Color(0xddb892)),

    /**
     * Text color
     */
    TEXT(new Color(0x7f5539)),

    /**
     * Border color
     */
    BORDER(new Color(0x9c6644));

    /**
     * Color
     */
    private Color color;

    /**
     * {@code Colors} constructor
     * 
     * @param color Color
     */
    private Colors(Color color) {
        this.color = color;
    }

    /**
     * Gets the color
     * 
     * @return Color
     */
    public Color getColor() {
        return color;
    }
}
