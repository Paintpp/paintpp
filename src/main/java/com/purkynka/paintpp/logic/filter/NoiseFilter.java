package com.purkynka.paintpp.logic.filter;

import com.purkynka.paintpp.logic.image.BufferBackedImage;
import com.purkynka.paintpp.logic.util.ColorUtil;

import java.awt.*;
import java.util.Random;

public class NoiseFilter extends ImageFilter {
    private int noiseAmount;
    private int noiseStrength;

    private double noiseColorRatio;

    private final Random random = new Random();

    public NoiseFilter(int noiseAmount, int noiseStrength) {
        this.noiseAmount = noiseAmount;
        this.noiseStrength = noiseStrength;

        this.recalculateNoiseColorRatio();
    }

    public void setNoiseAmount(int noiseAmount) {
        this.noiseAmount = noiseAmount;
    }

    public void setNoiseStrength(int noiseStrength) {
        this.noiseStrength = noiseStrength;
        this.recalculateNoiseColorRatio();
    }

    public void recalculateNoiseColorRatio() {
        noiseColorRatio = this.noiseStrength / 100d;
    }

    @Override
    public void modifyPixelBuffer(BufferBackedImage modifiedImage) {
        var imageSize = modifiedImage.getImageSize();
        var intBuffer = modifiedImage.getPixelIntBuffer();

        if (this.noiseAmount == 0) return;

        for (var i = 0; i < imageSize.totalPixels(); i++) {
            var randomValue = this.random.nextInt(101);
            if (randomValue > this.noiseAmount) continue;

            var randomColor = ColorUtil.getRandomColor(this.random);
            var color = new Color(intBuffer.get(i));

            var finalColor = ColorUtil.getInterpolatedColor(color, randomColor, this.noiseColorRatio);

            intBuffer.put(i, finalColor.getRGB());
        }
    }

    @Override
    public FilterType getFilterType() {
        return FilterType.NOISE;
    }
}
