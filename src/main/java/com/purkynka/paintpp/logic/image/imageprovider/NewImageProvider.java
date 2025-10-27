package com.purkynka.paintpp.logic.image.imageprovider;

import com.purkynka.paintpp.logic.image.ImageSize;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * {@link IImageProvider} for an empty {@link Image} filled with a single {@link Color}.
 */
public class NewImageProvider implements IImageProvider {
    private final ImageSize imageSize;
    private final Image image;

    /**
     * Constructs a {@link NewImageProvider}, creating an {@link Image} based on the provided
     * {@link ImageSize} and fill {@link Color}.
     * @param imageSize The image size of the generated image
     * @param fillColor The fill color of the generated image
     */
    public NewImageProvider(ImageSize imageSize, Color fillColor) {
        this.imageSize = imageSize;

        var writableImage = new WritableImage(imageSize.width(), imageSize.height());
        var pixelWriter = writableImage.getPixelWriter();

        for (var x = 0; x < imageSize.width(); x++) {
            for (var y = 0; y < imageSize.height(); y++) {
                pixelWriter.setColor(x, y, fillColor);
            }
        }

        image = writableImage;
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
