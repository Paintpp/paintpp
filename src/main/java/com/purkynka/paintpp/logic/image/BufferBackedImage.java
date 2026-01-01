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

    public BufferBackedImage(BufferBackedImage fromImage) {
        this(fromImage.imageSize);
        this.pixelIntBuffer.put(0, fromImage.pixelIntBuffer, 0, fromImage.imageSize.totalPixels());
    }

    public BufferBackedImage(Image image) {
        this(new IntSize((int) image.getWidth(), (int) image.getHeight()));
        var imageSize = this.imageSize;

        var pixelReader = image.getPixelReader();
        pixelReader.getPixels(
                0,
                0,
                imageSize.width,
                imageSize.height,
                this.pixelFormat,
                this.pixelIntBuffer,
                imageSize.width
        );
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
