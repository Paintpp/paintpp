package com.purkynka.paintpp.logic.filter;

public class SharpenFilter extends KernelFilter {
    public SharpenFilter() {
        super(true);
    }

    @Override
    protected double[][] constructKernel() {
        return new double[][] {
                {  0,  -1,  0 },
                { -1,   5, -1 },
                {  0,  -1,  0 }
        };
    }

    @Override
    public FilterType getFilterType() {
        return FilterType.SHARPEN;
    }
}
