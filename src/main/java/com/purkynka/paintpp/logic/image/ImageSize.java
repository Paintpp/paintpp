package com.purkynka.paintpp.logic.image;

import javafx.scene.image.Image;

/**
 * Utility record type for holding the {@link Image} width and height together.
 * @param width The width of the image in pixels
 * @param height The height of the image in pixels
 */
public record ImageSize(int width, int height) {
    /**
     * Returns the width of the image in pixels as a double.
     * @return The width of the image
     */
    public double widthAsDouble() {
        return width;
    }

    /**
     * Returns the height of the image in pixels as a double.
     * @return the height of the image
     */
    public double heightAsDouble() {
        return height;
    }
}
