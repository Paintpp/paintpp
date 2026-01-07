package com.purkynka.paintpp.logic.image.provider;

import com.purkynka.paintpp.logic.image.BufferBackedImage;
import com.purkynka.paintpp.logic.image.ImageGenerationType;
import com.purkynka.paintpp.logic.size.FloatSize;
import com.purkynka.paintpp.logic.size.IntSize;
import com.purkynka.paintpp.logic.util.BufferUtil;

import java.awt.*;
import java.nio.IntBuffer;

public class GeneratedImageProvider implements ImageProvider {
    private static final int MANDELBROT_ITERATIONS = 1000;
    private final IntSize imageSize;
    private final FloatSize imageFloatSize;
    private final BufferBackedImage bufferBackedImage;
    private final IntBuffer pixelIntBuffer;

    public GeneratedImageProvider(ImageGenerationType imageGenerationType, IntSize imageSize) {
        this.imageSize = imageSize;
        this.imageFloatSize = new FloatSize(imageSize);

        this.bufferBackedImage = new BufferBackedImage(imageSize);
        this.pixelIntBuffer = bufferBackedImage.getPixelIntBuffer();

        switch (imageGenerationType) {
            case X -> generateX();
            case Y -> generateY();
            case XY -> generateXY();
            case XY_AVERAGE -> generateXYAverage();
            case COS -> generateCos();
            case MANDELBROT -> generateMandelbrot();
        }
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

    private void generateXY() {
        for (var i = 0; i < this.imageSize.totalPixels(); i++) {
            var pixelPosition = BufferUtil.indexToPosition(i, this.imageSize.width);

            var xProgress = pixelPosition.x() / this.imageFloatSize.height;
            var yProgress = pixelPosition.y() / this.imageFloatSize.height;
            var color = new Color(xProgress, yProgress, 0);

            this.pixelIntBuffer.put(i, color.getRGB());
        }
    }

    private void generateXYAverage() {
        for (var i = 0; i < this.imageSize.totalPixels(); i++) {
            var pixelPosition = BufferUtil.indexToPosition(i, this.imageSize.width);

            var xProgress = pixelPosition.x() / this.imageFloatSize.height;
            var yProgress = pixelPosition.y() / this.imageFloatSize.height;
            var color = new Color(xProgress, yProgress, (xProgress + yProgress) / 2f);

            this.pixelIntBuffer.put(i, color.getRGB());
        }
    }

    private void generateCos() {
        for (var i = 0; i < this.imageSize.totalPixels(); i++) {
            var pixelPosition = BufferUtil.indexToPosition(i, this.imageSize.width);

            var xProgress = pixelPosition.x() / this.imageFloatSize.height;
            var yProgress = pixelPosition.y() / this.imageFloatSize.height;
            var color = new Color((float) Math.cos(xProgress), (float) Math.cos(yProgress), (xProgress + yProgress) / 2f);

            this.pixelIntBuffer.put(i, color.getRGB());
        }
    }

    private void generateMandelbrot() {
        var originalXRange = imageSize.width;
        var originalYRange = imageSize.height;

        var newXRange = (0.74 - (-2));
        var newYRange = (1.12 - (-1.12));

        for (var i = 0; i < this.imageSize.totalPixels(); i++) {
            var pixelPosition = BufferUtil.indexToPosition(i, this.imageSize.width);

            // (((OldValue - OldMin) * NewRange) / OldRange) + NewMin
            var mappedX = ((pixelPosition.x() * newXRange) / originalXRange) - 2;
            var mappedY = ((pixelPosition.y() * newYRange) / originalYRange) - 1.12;

            var generatedX = 0d;
            var generatedY = 0d;
            var iterations = 0;

            while (generatedX * generatedX + generatedY * generatedY <= 4 && iterations < MANDELBROT_ITERATIONS) {
                var xTemp = generatedX * generatedX - generatedY * generatedY + mappedX;
                generatedY = 2 * generatedX * generatedY + mappedY;
                generatedX = xTemp;

                iterations++;
            }

            if (iterations == MANDELBROT_ITERATIONS) {
                this.pixelIntBuffer.put(i, Color.BLACK.getRGB());
            } else {
                var usedIterations = Math.clamp((double) iterations / MANDELBROT_ITERATIONS + 0.25, 0, 1);
                var hue = Math.pow(usedIterations * 360, 1.5) % 360;

                var color = javafx.scene.paint.Color.hsb(hue, 1.0, usedIterations);

                this.pixelIntBuffer.put(i, new Color((int) (color.getGreen() * 255), (int) (color.getGreen() * 255), (int) (color.getBlue() * 255)).getRGB());
            }
        }
    }

    @Override
    public BufferBackedImage getBufferBackedImage() {
        return this.bufferBackedImage;
    }
}
