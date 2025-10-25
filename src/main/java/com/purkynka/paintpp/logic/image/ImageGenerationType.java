package com.purkynka.paintpp.logic.image;

public enum ImageGenerationType {
    Position("Position");

    private final String stringForm;

    ImageGenerationType(String stringForm) {
        this.stringForm = stringForm;
    }

    @Override
    public String toString() {
        return stringForm;
    }
}
