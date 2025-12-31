package com.purkynka.paintpp.logic.image.provider;

import com.purkynka.paintpp.logic.image.BufferBackedImage;
import com.purkynka.paintpp.logic.size.FloatSize;
import com.purkynka.paintpp.logic.size.IntSize;
import com.purkynka.paintpp.ui.stage.imagegenerator.GenerationType;

import java.awt.*;
import java.nio.IntBuffer;

public class GeneratedImageProvider implements ImageProvider {
    private final IntSize imageSize;
    private final FloatSize imageFloatSize;

    private final BufferBackedImage bufferBackedImage;
    private final IntBuffer pixelIntBuffer;

    public GeneratedImageProvider(GenerationType generationType, IntSize imageSize) {
        this.imageSize = imageSize;
        this.imageFloatSize = new FloatSize(imageSize);

        this.bufferBackedImage = new BufferBackedImage(imageSize);
        this.pixelIntBuffer = bufferBackedImage.getPixelIntBuffer();

        switch(generationType) {
            case X -> generateX();
            case Y -> generateY();
        };
    }

    private void generateX() {
        for (var i = 0; i < this.imageSize.totalPixels(); i++) {
            var x = i % this.imageSize.width;
            var xProgress = x / this.imageFloatSize.width;
            var color = new Color(xProgress, 0, 0);

            this.pixelIntBuffer.put(i, color.getRGB());
        }
    }

    private void generateY() {
        for (var i = 0; i < this.imageSize.totalPixels(); i++) {
            var y = i / this.imageSize.height;
            var yProgress = y / this.imageFloatSize.height;
            var color = new Color(0, yProgress, 0);

            this.pixelIntBuffer.put(i, color.getRGB());
        }
    }

    @Override
    public BufferBackedImage getBufferBackedImage() {
        return this.bufferBackedImage;
    }
}
