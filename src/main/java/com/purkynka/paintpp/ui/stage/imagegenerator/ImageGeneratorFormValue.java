package com.purkynka.paintpp.ui.stage.imagegenerator;

import com.purkynka.paintpp.logic.size.IntSize;

public class ImageGeneratorFormValue {
    public IntSize imageSize;
    public GenerationType generationType;

    public ImageGeneratorFormValue() {
        this.imageSize = new IntSize();
        this.generationType = null;
    }

    @Override
    public String toString() {
        return "ImageGeneratorFormValue{" +
                "imageSize=" + imageSize +
                ", generationType=" + generationType +
                '}';
    }
}
