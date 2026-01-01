package com.purkynka.paintpp.logic.filter;

import com.purkynka.paintpp.logic.util.BufferUtil;
import javafx.scene.image.PixelBuffer;

import java.awt.*;
import java.nio.IntBuffer;

public class PixelizeFilter extends ImageFilter {
    private int step;

    public PixelizeFilter(int step) {
        this.step = step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    @Override
    public void modifyPixelBuffer(PixelBuffer<IntBuffer> pixelBuffer) {
        var imageWidth = pixelBuffer.getWidth();
        var imageHeight = pixelBuffer.getHeight();
        var intBuffer = pixelBuffer.getBuffer();

        var intBufferCopy = IntBuffer.allocate(imageWidth * imageHeight);

        for (var y = 0; y < imageHeight; y += this.step) {
            for (var x = 0; x < imageWidth; x += this.step) {
                var totalPixels = 0;
                var newRed = 0;
                var newGreen = 0;
                var newBlue = 0;

                for (var yBlock = 0; yBlock < step; yBlock++) {
                    var newY = y + yBlock;
                    if (newY >= imageHeight) continue;

                    for (var xBlock = 0; xBlock < step; xBlock++) {
                        var newX = x + xBlock;
                        if (newX >= imageWidth) continue;

                        var index = BufferUtil.positionToIndex(newX, newY, imageWidth);
                        var color = new Color(intBuffer.get(index));

                        newRed += color.getRed();
                        newGreen += color.getGreen();
                        newBlue += color.getBlue();
                        totalPixels += 1;
                    }
                }

                var newColor = new Color(newRed / totalPixels, newGreen / totalPixels, newBlue / totalPixels);

                for (var yBlock = 0; yBlock < step; yBlock++) {
                    var newY = y + yBlock;
                    if (newY >= imageHeight) continue;

                    for (var xBlock = 0; xBlock < step; xBlock++) {
                        var newX = x + xBlock;
                        if (newX >= imageWidth) continue;

                        var index = BufferUtil.positionToIndex(newX, newY, imageWidth);
                        intBufferCopy.put(index, newColor.getRGB());
                    }
                }
            }
        }

        intBuffer.put(0, intBufferCopy, 0, imageWidth * imageHeight);
    }

    @Override
    public FilterType getFilterType() {
        return FilterType.PIXELIZE;
    }
}
