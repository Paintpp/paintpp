package com.purkynka.paintpp.logic.image;

public enum ImageGenerationType {
    Position("Position"),
    Sin("Sin"),
    CosTanTest("Cos Tan Test");

    private final String stringForm;

    ImageGenerationType(String stringForm) {
        this.stringForm = stringForm;
    }

    @Override
    public String toString() {
        return stringForm;
    }
}
