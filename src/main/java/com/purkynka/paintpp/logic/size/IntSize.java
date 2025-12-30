package com.purkynka.paintpp.logic.size;

public class IntSize {
    public Integer width;
    public Integer height;

    public IntSize(Integer width, Integer height) {
        this.width = width;
        this.height = height;
    }

    public IntSize() {}

    @Override
    public String toString() {
        return "IntSize{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
