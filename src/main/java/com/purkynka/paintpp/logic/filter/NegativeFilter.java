package com.purkynka.paintpp.logic.filter;

import com.purkynka.paintpp.ui.stage.filteradder.FilterType;
import javafx.scene.image.PixelBuffer;

import java.awt.*;
import java.nio.IntBuffer;

public class NegativeFilter extends ImageFilter {
    @Override
    public void modifyPixelBuffer(PixelBuffer<IntBuffer> pixelBuffer) {
        var intBuffer = pixelBuffer.getBuffer();

        for (var i = 0; i < pixelBuffer.getWidth() * pixelBuffer.getHeight(); i++) {
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
