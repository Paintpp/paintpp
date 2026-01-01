package com.purkynka.paintpp.logic.util;

import javafx.scene.image.PixelBuffer;

import java.awt.*;
import java.nio.IntBuffer;

public class BufferUtil {
    public static PixelPosition indexToPosition(int i, int imageWidth) {
        var x = i % imageWidth;
        var y = i / imageWidth;

        return new PixelPosition(x, y);
    }

    public static int positionToIndex(int x, int y, int imageWidth) {
        return x + y * imageWidth;
    }

    public static Color[] getSurroundingPixels(int pixelIndex, PixelBuffer<IntBuffer> pixelBuffer, int kernelWidth, int kernelHeight) {
        var intBuffer = pixelBuffer.getBuffer();
        var imageWidth = pixelBuffer.getWidth();
        var imageHeight = pixelBuffer.getHeight();

        var result = new Color[kernelWidth * kernelHeight];

        var position = BufferUtil.indexToPosition(pixelIndex, imageWidth);

        var horizontalRadius = kernelWidth / 2;
        var verticalRadius = kernelHeight / 2;

        for (var verticalOffset = -verticalRadius; verticalOffset <= verticalRadius; verticalOffset++) {
            for (var horizontalOffset = -horizontalRadius; horizontalOffset <= horizontalRadius; horizontalOffset++) {
                var newX = Math.clamp(position.x() + horizontalOffset, 0, imageWidth - 1);
                var newY = Math.clamp(position.y() + verticalOffset, 0, imageHeight - 1);

                var resultIndex = BufferUtil.positionToIndex(
                        horizontalOffset + horizontalRadius,
                        verticalOffset + verticalRadius,
                        kernelWidth
                );

//                if (newX < 0 || newX > imageWidth - 1 || newY < 0 || newY > imageHeight - 1) {
//                    result[resultIndex] = Color.BLACK;
//                    continue;
//                }

                var newPixelIndex = BufferUtil.positionToIndex(newX, newY, imageWidth);
                result[resultIndex] = new Color(intBuffer.get(newPixelIndex));
            }
        }

        return result;
    }
}
