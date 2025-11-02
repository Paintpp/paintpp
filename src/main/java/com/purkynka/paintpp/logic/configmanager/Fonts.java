package com.purkynka.paintpp.logic.configmanager;

public enum Fonts {
    System("Default"),
    Roboto("Roboto"),
    DejavuSans("Dejavu Sans"),
    Gentium("Gentium"),
    LiberationMono("Liberation Mono"),
    NimbusSans("Nimbus Sans");

    private final String displayName;

    Fonts(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
