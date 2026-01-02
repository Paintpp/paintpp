package com.purkynka.paintpp.logic.filter;

public class HorizontalEdgeDetect extends KernelFilter {
    public HorizontalEdgeDetect() {
        super(false);
    }

    @Override
    protected double[][] constructKernel() {
        return new double[][] {
                { 1, 0, -1 },
                { 2, 0, -2 },
                { 1, 0, -1 }
        };
    }

    @Override
    public FilterType getFilterType() {
        return FilterType.HORIZONTAL_EDGE_DETECT;
    }
}
