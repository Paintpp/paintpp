package com.purkynka.paintpp.logic.filter;

import com.purkynka.paintpp.logic.image.BufferBackedImage;
import com.purkynka.paintpp.logic.util.ColorUtil;

import java.awt.*;

public class BlackAndWhiteFilter extends ImageFilter {
    @Override
    public void modifyPixelBuffer(BufferBackedImage modifiedImage) {
        var imageSize = modifiedImage.getImageSize();
        var intBuffer = modifiedImage.getPixelIntBuffer();

        for (var i = 0; i < imageSize.totalPixels(); i++) {
            var color = new Color(intBuffer.get(i));
            var averageColor = ColorUtil.getAveragedColor(color);
            intBuffer.put(i, averageColor.getRGB());
        }
    }

    @Override
    public FilterType getFilterType() {
        return FilterType.BLACK_AND_WHITE;
    }
}
