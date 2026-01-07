package com.purkynka.paintpp.ui.stage.filter.custommatrix;

import java.util.ArrayList;

public class CustomMatrixFormValue {
    public ArrayList<ArrayList<Double>> kernel;
    public Boolean normalizeKernel;

    public CustomMatrixFormValue() {
        var kernel = new ArrayList<ArrayList<Double>>();

        for (var y = 0; y < 3; y++) {
            var line = new ArrayList<Double>();
            for (var x = 0; x < 3; x++) {
                line.add(0d);
            }
            kernel.add(line);
        }

        this.kernel = kernel;
        this.normalizeKernel = true;
    }
}
