package com.purkynka.paintpp.logic.image.imageprovider;

import com.purkynka.paintpp.logic.image.ImageGenerationType;
import com.purkynka.paintpp.logic.image.ImageSize;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class GeneratedImageProvider implements IImageProvider {
    private ImageSize imageSize;
    private Image image;

    public GeneratedImageProvider(ImageSize imageSize, ImageGenerationType imageGenerationType) {
        this.imageSize = imageSize;

        image = switch(imageGenerationType) {
            case Position -> positionGenerator();
        };
    }

    private Image positionGenerator() {
        var writableImage = new WritableImage(imageSize.width(), imageSize.height());
        var pixelWriter = writableImage.getPixelWriter();

        for (var x = 0; x < imageSize.width(); x++) {
            for (var y = 0; y < imageSize.height(); y++) {
                var r = (x / imageSize.width());
                var g = (y / imageSize.height());
                var b = (r + g) / 2;

                pixelWriter.setColor(x, y, new Color(r, g, b, 1));
            }
        }

        return writableImage;
    }

    @Override
    public Image getImage() {
        return null;
    }

    @Override
    public ImageSize getImageSize() {
        return imageSize;
    }
}
