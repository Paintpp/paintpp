package com.purkynka.paintpp.logic.image;

import com.purkynka.paintpp.logic.size.IntSize;
import javafx.scene.image.*;

import java.nio.IntBuffer;

public class BufferBackedImage {
    private final IntSize imageSize;
    private final IntBuffer pixelIntBuffer;
    private final WritablePixelFormat<IntBuffer> pixelFormat;
    private final PixelBuffer<IntBuffer> pixelBuffer;
    private final Image image;

    public BufferBackedImage(IntSize imageSize) {
        this.imageSize = imageSize;

        this.pixelIntBuffer = IntBuffer.allocate(this.imageSize.totalPixels());
        this.pixelFormat = PixelFormat.getIntArgbPreInstance();
        this.pixelBuffer = new PixelBuffer<>(this.imageSize.width, this.imageSize.height, this.pixelIntBuffer, pixelFormat);
        this.image = new WritableImage(this.pixelBuffer);
    }

    public IntSize getImageSize() {
        return this.imageSize;
    }

    public IntBuffer getPixelIntBuffer() {
        return this.pixelIntBuffer;
    }

    public WritablePixelFormat<IntBuffer> getPixelFormat() {
        return this.pixelFormat;
    }

    public PixelBuffer<IntBuffer> getPixelBuffer() {
        return this.pixelBuffer;
    }

    public Image getImage() {
        return this.image;
    }
}
