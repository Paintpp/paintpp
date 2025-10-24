package com.purkynka.paintpp.logic.image.imageprovider;

import com.purkynka.paintpp.logic.image.ImageSize;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class NewImageProvider implements IImageProvider {
    private final ImageSize imageSize;
    private final Image image;

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
