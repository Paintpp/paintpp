package com.purkynka.paintpp.logic.image.provider;

import com.purkynka.paintpp.logic.image.BufferBackedImage;
import com.purkynka.paintpp.logic.size.IntSize;
import javafx.scene.image.Image;

public class LoadedImageProvider implements ImageProvider {
    private final BufferBackedImage bufferBackedImage;

    public LoadedImageProvider(String loadedImagePath) {
        var image = new Image(loadedImagePath);

        this.bufferBackedImage = new BufferBackedImage(new IntSize((int) image.getWidth(), (int) image.getHeight()));
        var imageSize = this.bufferBackedImage.getImageSize();

        var pixelReader = image.getPixelReader();
        pixelReader.getPixels(
                0,
                0,
                imageSize.width,
                imageSize.height,
                this.bufferBackedImage.getPixelFormat(),
                this.bufferBackedImage.getPixelIntBuffer(),
                imageSize.width
        );
    }

    @Override
    public BufferBackedImage getBufferBackedImage() {
        return this.bufferBackedImage;
    }
}
