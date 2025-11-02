package com.purkynka.paintpp.ui.popup.settings.components.sidebar.pages;

public enum SettingPages {
    GENERAL(new GeneralPage()),
    APPEARANCE(new AppearancePage());

    private SettingsPage page;

    SettingPages(SettingsPage page) {
        this.page = page;
    }

    public SettingsPage getPage() {
        return page;
    }

    public void setPage(SettingsPage page) {
        this.page = page;
    }
}
