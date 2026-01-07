package com.purkynka.paintpp.logic.filter.kernelfilter;

import com.purkynka.paintpp.logic.filter.FilterType;

import java.util.ArrayList;

public class CustomMatrixFilter extends KernelFilter {
    private ArrayList<ArrayList<Double>> providedKernel;

    public CustomMatrixFilter(ArrayList<ArrayList<Double>> providedKernel, boolean normalizeKernel) {
        this.setProvidedKernel(providedKernel);
        this.setNormalizeKernel(normalizeKernel);
    }

    public void setProvidedKernel(ArrayList<ArrayList<Double>> providedKernel) {
        this.providedKernel = providedKernel;
        this.refreshKernel();
    }

    @Override
    protected double[][] constructKernel() {
        var height = providedKernel.size();
        var width = providedKernel.getFirst().size();

        var kernel = new double[height][width];
        for (var y = 0; y < height; y++) {
            for (var x = 0; x < width; x++) {
                kernel[y][x] = providedKernel.get(y).get(x);
            }
        }

        return kernel;
    }

    @Override
    public FilterType getFilterType() {
        return FilterType.CUSTOM_MATRIX;
    }

}
