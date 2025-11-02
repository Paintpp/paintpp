package com.purkynka.paintpp.logic.configmanager;

import atlantafx.base.theme.*;

public enum Themes {
    PrimerLight(new PrimerLight()),
    PrimerDark(new PrimerDark()),
    NordLight(new NordLight()),
    NordDark(new NordDark()),
    CupertinoDark(new CupertinoDark()),
    CupertinoLight(new CupertinoLight());

    private final Theme theme;

    Themes(Theme theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
        return theme.getName();
    }

    public String getUserAgentStylesheet() {
        return theme.getUserAgentStylesheet();
    }
}