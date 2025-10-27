package com.purkynka.paintpp.logic.image;

import com.purkynka.paintpp.logic.image.imageprovider.GeneratedImageProvider;

/**
 * Enum for the various generation types used in {@link GeneratedImageProvider}.
 */
public enum ImageGenerationType {
    XCoordinate("X Coordinate"),
    YCoordinate("Y Coordinate"),
    XYCoordinates("XY Coordinates"),
    XYCoordinatesAverage("XY Coordinates with Average"),
    Sin("Sin of Coordinates"),
    Circle("Circle"),
    CircleFalloff("Circle with Falloff"),
    Mandelbrot("Mandelbrot (1000 iterations)");

    private final String stringForm;

    /**
     * Constructs a new {@link ImageGenerationType} represented by the provided {@link String}.
     * @param stringForm The string representation of the enum value.
     */
    ImageGenerationType(String stringForm) {
        this.stringForm = stringForm;
    }

    @Override
    public String toString() {
        return stringForm;
    }
}
