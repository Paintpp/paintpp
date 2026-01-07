package com.purkynka.paintpp.logic.filter.kernelfilter;

import com.purkynka.paintpp.logic.filter.FilterType;

public class GaussianBlurFilter extends KernelFilter {
    private int kernelSize;
    private int sigma;

    public GaussianBlurFilter(int kernelSize, int sigma) {
        this.kernelSize = kernelSize;
        this.sigma = sigma;

        this.normalizeKernel = true;
        this.refreshKernel();
    }

    public void setKernelSize(int kernelSize) {
        this.kernelSize = kernelSize;
        this.refreshKernel();
    }

    public void setSigma(int sigma) {
        this.sigma = sigma;
        this.refreshKernel();
    }

    private double calculateGaussian(int x, int y, int sigma) {
        return Math.exp(-(x * x + y * y) / (2.0 * sigma * sigma));
    }

    @Override
    protected double[][] constructKernel() {
        var kernel = new double[kernelSize][kernelSize];

        var kernelSizeHalved = this.kernelSize / 2;

        for (var y = -kernelSizeHalved; y <= kernelSizeHalved; y++) {
            var actualY = y + kernelSizeHalved;

            for (var x = -kernelSizeHalved; x <= kernelSizeHalved; x++) {
                var actualX = x + kernelSizeHalved;
                kernel[actualY][actualX] = this.calculateGaussian(x, y, this.sigma);
            }
        }

        return kernel;
    }

    @Override
    public FilterType getFilterType() {
        return FilterType.GAUSSIAN_BLUR;
    }
}
