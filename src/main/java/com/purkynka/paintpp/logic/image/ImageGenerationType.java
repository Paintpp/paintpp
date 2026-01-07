package com.purkynka.paintpp.logic.image;

import com.purkynka.paintpp.ui.element.form.input.choicefield.DescriptiveEnum;

public enum ImageGenerationType implements DescriptiveEnum {
    X("X Position", "R: x"),
    Y("Y Position", "G: y"),
    XY("X & Y Position", "R: x, G: y"),
    XY_AVERAGE("X & Y Position with Average", "R: x, G: y, B: avg(x + y)"),
    COS("Cos", "R: cos(x), G: cos(y), B: avg(x + y)"),
    MANDELBROT("Mandelbrot", "Mandelbrot set with 1000 iterations.\nQuite slow to generate!");

    private final String name;
    private final String description;

    ImageGenerationType(String name, String description) {
        this.name = name;
        this.description = this.highlightVariables(description);
    }

    private String highlightVariables(String text) {
        var result = text.replaceAll("R", "[color=-color-danger-fg]R[/color]");
        result = result.replaceAll("G", "[color=-color-success-fg]G[/color]");
        result = result.replaceAll("B", "[color=-color-accent-fg]B[/color]");
        result = result.replaceAll("x", "[color=-color-fg-default]x[/color]");
        result = result.replaceAll("y", "[color=-color-fg-default]y[/color]");
        result = result.replaceAll("\\d+", "[color=-color-fg-default]$0[/color]");

        return result;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
