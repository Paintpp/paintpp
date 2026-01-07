package com.purkynka.paintpp.ui.stage.imagegenerator;

import com.purkynka.paintpp.logic.image.ImageGenerationType;
import com.purkynka.paintpp.logic.size.IntSize;

public class ImageGeneratorFormValue {
    public IntSize imageSize;
    public ImageGenerationType imageGenerationType;

    public ImageGeneratorFormValue() {
        this.imageSize = new IntSize();
        this.imageGenerationType = null;
    }

    @Override
    public String toString() {
        return "ImageGeneratorFormValue{" +
                "imageSize=" + imageSize +
                ", generationType=" + imageGenerationType +
                '}';
    }
}
