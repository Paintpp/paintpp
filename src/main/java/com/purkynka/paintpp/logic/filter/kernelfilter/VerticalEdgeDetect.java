package com.purkynka.paintpp.logic.filter.kernelfilter;

import com.purkynka.paintpp.logic.filter.FilterType;

public class VerticalEdgeDetect extends KernelFilter {
    public VerticalEdgeDetect() {
        super(false);
    }

    @Override
    protected double[][] constructKernel() {
        return new double[][] {
                { 1, 2, 1 },
                { 0, 0, 0 },
                { -1, -2, -1 }
        };
    }

    @Override
    public FilterType getFilterType() {
        return FilterType.VERTICAL_EDGE_DETECT;
    }
}
