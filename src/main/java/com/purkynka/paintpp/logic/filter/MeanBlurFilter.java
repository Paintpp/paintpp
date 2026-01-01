package com.purkynka.paintpp.logic.filter;

public class MeanBlurFilter extends KernelFilter {
    public MeanBlurFilter() {
        super(true);
    }

    @Override
    protected double[][] constructKernel() {
        return new double[][] {
                { 1, 1, 1 },
                { 1, 1, 1 },
                { 1, 1, 1 }
        };
    }

    @Override
    public FilterType getFilterType() {
        return FilterType.MEAN_BLUR;
    }
}
