package com.purkynka.paintpp.logic.size;

public class FloatSize {
    public Float width;
    public Float height;

    public FloatSize(Float width, Float height) {
        this.width = width;
        this.height = height;
    }

    public FloatSize(IntSize intSize) {
        this(intSize.width.floatValue(), intSize.height.floatValue());
    }

    public FloatSize() {
    }

    public float totalPixels() {
        return width * height;
    }
}
