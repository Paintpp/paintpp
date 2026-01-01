package com.purkynka.paintpp.logic.filter;

import com.purkynka.paintpp.logic.image.BufferBackedImage;
import javafx.scene.image.PixelBuffer;

import java.awt.*;
import java.nio.IntBuffer;

public class NegativeFilter extends ImageFilter {
    @Override
    public void modifyPixelBuffer(BufferBackedImage modifiedImage) {
        var imageSize = modifiedImage.getImageSize();
        var intBuffer = modifiedImage.getPixelIntBuffer();

        for (var i = 0; i < imageSize.totalPixels(); i++) {
            var colorInt = intBuffer.get(i);
            var color = new Color(colorInt);
            var newColor = new Color(
                    255 - color.getRed(),
                    255 - color.getGreen(),
                    255 - color.getBlue()
            );

            intBuffer.put(i, newColor.getRGB());
        }
    }

    @Override
    public FilterType getFilterType() {
        return FilterType.NEGATIVE;
    }
}
