package com.purkynka.paintpp.logic.image;

public record ImageSize(int width, int height) {
    public double widthAsDouble() {
        return width;
    }
    public double heightAsDouble() {
        return height;
    }
}
