package com.purkynka.paintpp.ui.popup.help;

public class HelpSection {
    private final String name;
    private final String content;

    public HelpSection(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }
}
