package com.purkynka.paintpp.logic.filter.imagefilter;

import com.purkynka.paintpp.logic.filter.FilterType;
import com.purkynka.paintpp.logic.image.BufferBackedImage;

import java.awt.*;

public class ColorTemperatureFilter extends ImageFilter {
    private double redModifier;
    private double blueModifier;

    public ColorTemperatureFilter(int temperatureModifier) {
        this.adjustModifiers(temperatureModifier);
    }

    public void adjustModifiers(int temperatureModifier) {
        this.redModifier = 1 + 0.1d * temperatureModifier;
        this.blueModifier = 1 - 0.1d * temperatureModifier;
    }

    @Override
    public void modifyPixelBuffer(BufferBackedImage modifiedImage) {
        var imageSize = modifiedImage.getImageSize();
        var intBuffer = modifiedImage.getPixelIntBuffer();

        for (var i = 0; i < imageSize.totalPixels(); i++) {
            var color = new Color(intBuffer.get(i));

            var newRed = Math.clamp(color.getRed() * this.redModifier, 0, 255);
            var newBlue = Math.clamp(color.getBlue() * this.blueModifier, 0, 255);

            var newColor = new Color((int) newRed, color.getGreen(), (int) newBlue);
            intBuffer.put(i, newColor.getRGB());
        }
    }

    @Override
    public FilterType getFilterType() {
        return FilterType.COLOR_TEMPERATURE;
    }
}
