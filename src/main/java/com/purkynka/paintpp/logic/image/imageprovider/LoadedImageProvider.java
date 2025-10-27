package com.purkynka.paintpp.logic.image.imageprovider;

import com.purkynka.paintpp.logic.image.ImageSize;
import javafx.scene.image.Image;

import java.io.File;

/**
 * {@link IImageProvider} for an {@link Image} loaded from disk.
 */
public class LoadedImageProvider implements IImageProvider {
    private final ImageSize imageSize;
    private final Image image;

    /**
     * Constructs a new {@link LoadedImageProvider} from the provided {@link File}.
     * @param imageFile The file to load the image from
     */
    public LoadedImageProvider(File imageFile) {
        image = new Image(imageFile.toURI().toString());
        imageSize = new ImageSize((int) image.getWidth(), (int) image.getHeight());
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public ImageSize getImageSize() {
        return imageSize;
    }
}
