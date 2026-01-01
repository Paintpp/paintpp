package com.purkynka.paintpp.ui.stage.filteradder;

import com.purkynka.paintpp.logic.filter.*;
import com.purkynka.paintpp.ui.element.form.input.choicefield.DescriptiveEnum;
import com.purkynka.paintpp.ui.stage.filter.PixelizeFilterAdder;

public enum FilterType implements DescriptiveEnum {
    NEGATIVE("Negative", "Inverts the colors of the image."),
    PIXELIZE("Pixelize", "Pixelizes the image with a stepping function."),
    IDENTITY("Identity", "Passes over the image with no changes."),
    THRESHOLD("Threshold", "Turns the pixels over the threshold white and the\nrest black."),
    NOISE("Noise", "Applies noise over the image."),
    BLACK_AND_WHITE("Black & White", "Turns the image grayscale."),
    MEAN_BLUR("Mean Blur", "Applies a 3x3 mean blur over the image."),
    GAUSSIAN_BLUR("Gaussian Blur", "Applies a 3x3 Gaussian blur to the image."),
    SHARPEN("Sharpen", "Sharpens the image."),
    COLORIZER("Colorizer", "Adds an amount of color to each pixel.");

    private final String name;
    private final String description;

    FilterType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addOrConfigure() {
        switch (this) {
            case NEGATIVE -> FilterManager.FILTERS.add(new NegativeFilter());
            case PIXELIZE -> new PixelizeFilterAdder().open();
            case IDENTITY -> {}
            case THRESHOLD -> {}
            case NOISE -> {}
            case BLACK_AND_WHITE -> {}
            case MEAN_BLUR -> FilterManager.FILTERS.add(new MeanBlurFilter());
            case GAUSSIAN_BLUR -> FilterManager.FILTERS.add(new GaussianBlurFilter());
            case SHARPEN -> FilterManager.FILTERS.add(new SharpenFilter());
            case COLORIZER -> {}
        }
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
