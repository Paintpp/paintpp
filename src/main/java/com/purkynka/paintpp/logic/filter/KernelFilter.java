package com.purkynka.paintpp.logic.filter;

import com.purkynka.paintpp.logic.util.BufferUtil;
import javafx.scene.image.PixelBuffer;

import java.awt.*;
import java.nio.IntBuffer;
import java.util.Arrays;

public abstract class KernelFilter extends ImageFilter {
    protected final double[] kernel;
    protected final int kernelWidth;
    protected final int kernelHeight;

    public KernelFilter(boolean normalizeKernel) {
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
    public void modifyPixelBuffer(PixelBuffer<IntBuffer> pixelBuffer) {
        var intBuffer = pixelBuffer.getBuffer();
        var imageWidth = pixelBuffer.getWidth();
        var imageHeight = pixelBuffer.getHeight();

        var intBufferCopy = IntBuffer.allocate(imageWidth * imageHeight);

        for (var pixelIndex = 0; pixelIndex < imageWidth * imageHeight; pixelIndex++) {
            var surroundingPixels = BufferUtil.getSurroundingPixels(pixelIndex, pixelBuffer, this.kernelWidth, this.kernelHeight);

            var newColor = applyKernel(surroundingPixels);

            intBufferCopy.put(pixelIndex, newColor.getRGB());
        }

        intBuffer.put(0, intBufferCopy, 0, imageWidth * imageHeight);
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
