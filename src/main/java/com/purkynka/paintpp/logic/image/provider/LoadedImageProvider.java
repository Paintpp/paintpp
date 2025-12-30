package com.purkynka.paintpp.logic.image.provider;

import javafx.scene.image.Image;

public class LoadedImageProvider implements ImageProvider {
    private final Image loadedImage;

    public LoadedImageProvider(String loadedImagePath) {
        this.loadedImage = new Image(loadedImagePath);
    }

    @Override
    public Image getImage() {
        return loadedImage;
    }
}
