package com.purkynka.paintpp.logic.filter.kernelfilter;

import com.purkynka.paintpp.logic.filter.imagefilter.ImageFilter;
import com.purkynka.paintpp.logic.image.BufferBackedImage;
import com.purkynka.paintpp.logic.util.BufferUtil;

import java.awt.*;
import java.nio.IntBuffer;
import java.util.Arrays;

public abstract class KernelFilter extends ImageFilter {
    protected boolean normalizeKernel;
    protected double[] kernel;
    protected int kernelWidth;
    protected int kernelHeight;

    public KernelFilter() {
    }

    public KernelFilter(boolean normalizeKernel) {
        this.normalizeKernel = normalizeKernel;

        this.refreshKernel();
    }

    public void setNormalizeKernel(boolean normalizeKernel) {
        this.normalizeKernel = normalizeKernel;
        this.refreshKernel();
    }

    protected void refreshKernel() {
        var kernel = this.constructKernel();

        this.kernelWidth = kernel[0].length;
        this.kernelHeight = kernel.length;

        this.kernel = Arrays.stream(kernel).flatMapToDouble(Arrays::stream).toArray();
        if (normalizeKernel) this.normalizeKernel();
    }

    private void normalizeKernel() {
        var sum = 0d;

        for (double v : this.kernel) {
            sum += v;
        }

        sum = 1 / sum;

        for (int i = 0; i < this.kernel.length; i++) {
            this.kernel[i] *= sum;
        }
    }

    protected abstract double[][] constructKernel();

    @Override
    public void modifyPixelBuffer(BufferBackedImage modifiedImage) {
        var imageSize = modifiedImage.getImageSize();
        var intBuffer = modifiedImage.getPixelIntBuffer();
        var pixelBuffer = modifiedImage.getPixelBuffer();

        var intBufferCopy = IntBuffer.allocate(imageSize.totalPixels());

        for (var pixelIndex = 0; pixelIndex < imageSize.totalPixels(); pixelIndex++) {
            var surroundingPixels = BufferUtil.getSurroundingPixels(pixelIndex, pixelBuffer, this.kernelWidth, this.kernelHeight);

            var newColor = applyKernel(surroundingPixels);

            intBufferCopy.put(pixelIndex, newColor.getRGB());
        }

        intBuffer.put(0, intBufferCopy, 0, imageSize.totalPixels());
    }

    private Color applyKernel(Color[] surroundingPixels) {
        var newRed = 0d;
        var newGreen = 0d;
        var newBlue = 0d;

        for (int kernelIndex = 0; kernelIndex < this.kernel.length; kernelIndex++) {
            var color = surroundingPixels[kernelIndex];

            if (color == null) continue;

            newRed += color.getRed() * this.kernel[kernelIndex];
            newGreen += color.getGreen() * this.kernel[kernelIndex];
            newBlue += color.getBlue() * this.kernel[kernelIndex];
        }

        return new Color((int) Math.clamp(newRed, 0, 255), (int) Math.clamp(newGreen, 0, 255), (int) Math.clamp(newBlue, 0, 255));
    }
}
