package com.purkynka.paintpp.logic.image.provider;

import com.purkynka.paintpp.logic.size.FloatSize;
import com.purkynka.paintpp.logic.size.IntSize;
import com.purkynka.paintpp.ui.stage.imagegenerator.GenerationType;
import javafx.scene.image.Image;
import javafx.scene.image.PixelBuffer;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;

import java.awt.*;
import java.nio.IntBuffer;

public class GeneratedImageProvider implements ImageProvider {
    private final IntSize imageSize;
    private final FloatSize imageFloatSize;

    private final Image generatedImage;

    public GeneratedImageProvider(GenerationType generationType, IntSize imageSize) {
        this.imageSize = imageSize;
        this.imageFloatSize = new FloatSize(imageSize);

        var intBuffer = IntBuffer.allocate(imageSize.width * imageSize.height);
        var pixelFormat = PixelFormat.getIntArgbPreInstance();
        var pixelBuffer = new PixelBuffer<>(imageSize.width, imageSize.height, intBuffer, pixelFormat);

        switch(generationType) {
            case X -> generateX(intBuffer);
            case Y -> generateY(intBuffer);
        };

        this.generatedImage = new WritableImage(pixelBuffer);
    }

    private void generateX(IntBuffer imageBuffer) {
        for (var i = 0; i < this.imageSize.totalPixels(); i++) {
            var x = i % imageSize.width;
            var xProgress = x / imageFloatSize.width;
            var color = new Color(xProgress, 0, 0);

            imageBuffer.put(i, color.getRGB());
        }
    }

    private void generateY(IntBuffer imageBuffer) {
        for (var i = 0; i < this.imageSize.totalPixels(); i++) {
            var y = i / imageSize.height;
            var yProgress = y / imageFloatSize.height;
            var color = new Color(0, yProgress, 0);

            imageBuffer.put(i, color.getRGB());
        }
    }

    @Override
    public Image getImage() {
        return this.generatedImage;
    }

    @Override
    public IntSize getImageSize() {
        return this.imageSize;
    }
}
