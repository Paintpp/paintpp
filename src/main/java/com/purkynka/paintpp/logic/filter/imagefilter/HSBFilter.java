package com.purkynka.paintpp.logic.filter.imagefilter;

import com.purkynka.paintpp.logic.filter.FilterType;
import com.purkynka.paintpp.logic.image.BufferBackedImage;

import java.awt.*;

public class HSBFilter extends ImageFilter {
    private int hueModifier;
    private double hueValue;

    private int saturationModifier;
    private double saturationValue;

    private int brightnessModifier;
    private double brightnessValue;

    public HSBFilter(int hueModifier, int saturationModifier, int brightnessModifier) {
        this.setHueModifier(hueModifier);
        this.setSaturationModifier(saturationModifier);
        this.setBrightnessModifier(brightnessModifier);
    }

    public void setHueModifier(int hueModifier) {
        this.hueModifier = hueModifier;
        this.hueValue = hueModifier / 360d;
    }

    public void setSaturationModifier(int saturationModifier) {
        this.saturationModifier = saturationModifier;
        this.saturationValue = saturationModifier / 100d;
    }

    public void setBrightnessModifier(int brightnessModifier) {
        this.brightnessModifier = brightnessModifier;
        this.brightnessValue = brightnessModifier / 100d;
    }

    @Override
    public void modifyPixelBuffer(BufferBackedImage modifiedImage) {
        var imageSize = modifiedImage.getImageSize();
        var intBuffer = modifiedImage.getPixelIntBuffer();

        for (var i = 0; i < imageSize.totalPixels(); i++) {
            var color = new Color(intBuffer.get(i));
            var hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);

            var newColor = Color.getHSBColor(
                    (float) (hsb[0] + this.hueValue),
                    (float) Math.clamp(hsb[1] + this.saturationValue, 0, 1),
                    (float) Math.clamp(hsb[2] + this.brightnessValue, 0, 1)
            );
            intBuffer.put(i, newColor.getRGB());
        }
    }

    @Override
    public FilterType getFilterType() {
        return FilterType.HSB;
    }
}
