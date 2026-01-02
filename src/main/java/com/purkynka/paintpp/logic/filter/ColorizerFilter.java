package com.purkynka.paintpp.logic.filter;

import com.purkynka.paintpp.logic.image.BufferBackedImage;

import java.awt.*;

public class ColorizerFilter extends ImageFilter {
    private int redAmount;
    private int greenAmount;
    private int blueAmount;

    public ColorizerFilter(int redAmount, int greenAmount, int blueAmount) {
        this.redAmount = redAmount;
        this.greenAmount = greenAmount;
        this.blueAmount = blueAmount;
    }

    public void setRedAmount(int redAmount) {
        this.redAmount = redAmount;
    }

    public void setGreenAmount(int greenAmount) {
        this.greenAmount = greenAmount;
    }

    public void setBlueAmount(int blueAmount) {
        this.blueAmount = blueAmount;
    }

    @Override
    public void modifyPixelBuffer(BufferBackedImage modifiedImage) {
        var imageSize = modifiedImage.getImageSize();
        var intBuffer = modifiedImage.getPixelIntBuffer();

        for (var i = 0; i < imageSize.totalPixels(); i++) {
            var color = new Color(intBuffer.get(i));

            var newRed = Math.clamp(color.getRed() + this.redAmount, 0, 255);
            var newGreen = Math.clamp(color.getGreen() + this.greenAmount, 0, 255);
            var newBlue = Math.clamp(color.getBlue() + this.blueAmount, 0, 255);

            var newColor = new Color(newRed, newGreen, newBlue);

            intBuffer.put(i, newColor.getRGB());
        }
    }

    @Override
    public FilterType getFilterType() {
        return FilterType.COLORIZER;
    }
}
