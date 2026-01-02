package com.purkynka.paintpp.logic.filter;

public class EmbossFilter extends KernelFilter {
    public EmbossFilter() {
        super(true);
    }

    @Override
    protected double[][] constructKernel() {
        return new double[][] {
                { -2, -1, 0 },
                { -1, 1, 1 },
                { 0, 1, 2 }
        };
    }

    @Override
    public FilterType getFilterType() {
        return FilterType.EMBOSS;
    }
}
