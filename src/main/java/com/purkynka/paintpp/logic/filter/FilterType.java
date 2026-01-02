package com.purkynka.paintpp.logic.filter;

import com.purkynka.paintpp.ui.element.form.input.choicefield.DescriptiveEnum;
import com.purkynka.paintpp.ui.stage.filter.colorizer.ColorizerFilterAdder;
import com.purkynka.paintpp.ui.stage.filter.gaussianblur.GaussianBlurFilterAdder;
import com.purkynka.paintpp.ui.stage.filter.noise.NoiseFilterAdder;
import com.purkynka.paintpp.ui.stage.filter.pixelize.PixelizeFilterAdder;
import com.purkynka.paintpp.ui.stage.filter.threshold.ThresholdFilterAdder;

public enum FilterType implements DescriptiveEnum {
    NEGATIVE("Negative", "Inverts the colors of the image."),
    PIXELIZE("Pixelize", "Pixelizes the image with a stepping function."),
    THRESHOLD("Threshold", "Turns the pixels over the threshold white and the\nrest black."),
    NOISE("Noise", "Applies noise over the image."),
    BLACK_AND_WHITE("Black & White", "Turns the image grayscale."),
    GAUSSIAN_BLUR("Gaussian Blur", "Applies a Gaussian blur to the image."),
    SHARPEN("Sharpen", "Sharpens the image."),
    COLORIZER("Colorizer", "Adds a flat amount of color to each pixel."),
    HORIZONTAL_EDGE_DETECT("Horizontal Edge Detect", "Highlights horizontal edges using a Sobel kernel."),
    VERTICAL_EDGE_DETECT("Vertical Edge Detect", "Highlights vertical edges using a Sobel kernel.");

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
            case THRESHOLD -> new ThresholdFilterAdder().open();
            case NOISE -> new NoiseFilterAdder().open();
            case BLACK_AND_WHITE -> FilterManager.FILTERS.add(new BlackAndWhiteFilter());
            case GAUSSIAN_BLUR -> new GaussianBlurFilterAdder().open();
            case SHARPEN -> FilterManager.FILTERS.add(new SharpenFilter());
            case COLORIZER -> new ColorizerFilterAdder().open();
            case HORIZONTAL_EDGE_DETECT -> FilterManager.FILTERS.add(new HorizontalEdgeDetect());
            case VERTICAL_EDGE_DETECT -> FilterManager.FILTERS.add(new VerticalEdgeDetect());
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
