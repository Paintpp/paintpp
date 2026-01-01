package com.purkynka.paintpp.logic.filter;

import com.purkynka.paintpp.logic.image.BufferBackedImage;
import com.purkynka.paintpp.logic.util.ColorUtil;

import java.awt.*;

public class ThresholdFilter extends ImageFilter {
    private int threshold;

    public ThresholdFilter(int threshold) {
        this.threshold = threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public void modifyPixelBuffer(BufferBackedImage modifiedImage) {
        var imageSize = modifiedImage.getImageSize();
        var intBuffer = modifiedImage.getPixelIntBuffer();

        for (var i = 0; i < imageSize.totalPixels(); i++) {
            var color = new Color(intBuffer.get(i));
            var averagedColor = ColorUtil.getAveragedColor(color);

            intBuffer.put(i, averagedColor.getRed() > this.threshold ? Color.WHITE.getRGB() : Color.BLACK.getRGB());
        }
    }

    @Override
    public FilterType getFilterType() {
        return FilterType.THRESHOLD;
    }
}
