package com.purkynka.paintpp.logic.filter;

public class GaussianBlurFilter extends KernelFilter {
    public GaussianBlurFilter() {
        super(true);
    }

    @Override
    protected double[][] constructKernel() {
        return new double[][] {
                {   1,  2,  1   },
                {   2,  4,  2   },
                {   1,  2,  1   },
        };
    }

    @Override
    public FilterType getFilterType() {
        return FilterType.GAUSSIAN_BLUR;
    }
}
