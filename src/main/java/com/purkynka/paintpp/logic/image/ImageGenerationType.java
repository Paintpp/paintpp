package com.purkynka.paintpp.logic.image;

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

    ImageGenerationType(String stringForm) {
        this.stringForm = stringForm;
    }

    @Override
    public String toString() {
        return stringForm;
    }
}
