package com.ccprog3.gui;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * Panel to show text
 * 
 * @author Justin Ryan Uy
 */
public class TextPanel extends JPanel {
    /**
     * Constructor for the component
     */
    public TextPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }
}
