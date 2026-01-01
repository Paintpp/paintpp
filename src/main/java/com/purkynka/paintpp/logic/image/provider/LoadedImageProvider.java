package com.purkynka.paintpp.logic.image.provider;

import com.purkynka.paintpp.logic.image.BufferBackedImage;
import com.purkynka.paintpp.logic.size.IntSize;
import javafx.scene.image.Image;

public class LoadedImageProvider implements ImageProvider {
    private final BufferBackedImage bufferBackedImage;

    public LoadedImageProvider(String loadedImagePath) {
        var image = new Image(loadedImagePath);

        this.bufferBackedImage = new BufferBackedImage(image);
    }

    @Override
    public BufferBackedImage getBufferBackedImage() {
        return this.bufferBackedImage;
    }
}
