package com.purkynka.paintpp.logic.filter;

import javafx.scene.image.PixelBuffer;

import java.nio.IntBuffer;

public abstract class ImageFilter {
    public abstract void modifyPixelBuffer(PixelBuffer<IntBuffer> pixelBuffer);
    public abstract FilterType getFilterType();
}
