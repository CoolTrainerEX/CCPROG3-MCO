package com.ccprog3.gui;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.ccprog3.GUISingleton;

/**
 * Image library
 */
public enum Images {
    /**
     * App title
     */
    TITLE("title.png"),

    /**
     * App icon
     */
    FAVICON("favicon.png"),

    /**
     * Frame background
     */
    FRAME_BACKGROUND("frameBackground.jpg"),

    /**
     * Primary background
     */
    PRIMARY_BACKGROUND("primaryBackground.jpg");

    /**
     * Image
     */
    private final BufferedImage image;

    /**
     * {@code Images} constructor
     * 
     * @param name Name of the desired resource
     */
    private Images(String name) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(name));
        } catch (IOException e) {
            GUISingleton.getInstance().displayErr(new IOException("Image not found"));
        }

        this.image = image;
    }

    /**
     * Gets the image
     * 
     * @return Image
     */
    public BufferedImage getImage() {
        return image;
    }
}
