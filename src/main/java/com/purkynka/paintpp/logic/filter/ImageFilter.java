package com.purkynka.paintpp.logic.filter;

import com.purkynka.paintpp.logic.image.BufferBackedImage;
import javafx.scene.image.PixelBuffer;

import java.nio.IntBuffer;

public abstract class ImageFilter {
    public abstract void modifyPixelBuffer(BufferBackedImage modifiedImage);
    public abstract FilterType getFilterType();
}
