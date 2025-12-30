package com.purkynka.paintpp.logic.image.provider;

import com.purkynka.paintpp.logic.size.IntSize;
import javafx.scene.image.Image;

public class LoadedImageProvider implements ImageProvider {
    private final Image loadedImage;
    private final IntSize imageSize;

    public LoadedImageProvider(String loadedImagePath) {
        this.loadedImage = new Image(loadedImagePath);
        this.imageSize = new IntSize((int) this.loadedImage.getWidth(), (int) this.loadedImage.getHeight());
    }

    @Override
    public Image getImage() {
        return loadedImage;
    }

    @Override
    public IntSize getImageSize() {
        return this.imageSize;
    }
}
