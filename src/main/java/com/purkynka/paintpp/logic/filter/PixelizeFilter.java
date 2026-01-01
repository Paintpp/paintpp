package com.purkynka.paintpp.logic.filter;

import com.purkynka.paintpp.logic.image.BufferBackedImage;
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
    public void modifyPixelBuffer(BufferBackedImage modifiedImage) {
        var imageSize = modifiedImage.getImageSize();
        var intBuffer = modifiedImage.getPixelIntBuffer();

        var intBufferCopy = IntBuffer.allocate(imageSize.totalPixels());

        for (var y = 0; y < imageSize.height; y += this.step) {
            for (var x = 0; x < imageSize.width; x += this.step) {
                var totalPixels = 0;
                var newRed = 0;
                var newGreen = 0;
                var newBlue = 0;

                for (var yBlock = 0; yBlock < step; yBlock++) {
                    var newY = y + yBlock;
                    if (newY >= imageSize.height) continue;

                    for (var xBlock = 0; xBlock < step; xBlock++) {
                        var newX = x + xBlock;
                        if (newX >= imageSize.width) continue;

                        var index = BufferUtil.positionToIndex(newX, newY, imageSize.width);
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
                    if (newY >= imageSize.height) continue;

                    for (var xBlock = 0; xBlock < step; xBlock++) {
                        var newX = x + xBlock;
                        if (newX >= imageSize.width) continue;

                        var index = BufferUtil.positionToIndex(newX, newY, imageSize.width);
                        intBufferCopy.put(index, newColor.getRGB());
                    }
                }
            }
        }

        intBuffer.put(0, intBufferCopy, 0, imageSize.totalPixels());
    }

    @Override
    public FilterType getFilterType() {
        return FilterType.PIXELIZE;
    }
}
