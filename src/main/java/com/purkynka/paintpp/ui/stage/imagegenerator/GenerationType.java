package com.purkynka.paintpp.ui.stage.imagegenerator;

import com.purkynka.paintpp.ui.element.form.input.choicefield.DescriptiveEnum;

public enum GenerationType implements DescriptiveEnum {
    X("X Position", "Red: x"),
    Y("Y Position", "Green: y");

    private final String name;
    private final String description;

    GenerationType(String name, String description) {
        this.name = name;
        this.description = this.highlightVariables(description);
    }

    private String highlightVariables(String text) {
        var result = text.replaceAll("Red", "[color=-color-danger-fg]R[/color]");
        result = result.replaceAll("Green", "[color=-color-success-fg]G[/color]");
        result = result.replaceAll("Blue", "[color=-color-accent-fg]B[/color]");
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
