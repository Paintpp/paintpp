package com.purkynka.paintpp.ui.element.filterloadingscreen;

import atlantafx.base.theme.Styles;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignL;

public class FilterLoadingScreen extends VBox {
    public static FilterLoadingScreen FILTER_LOADING_SCREEN;

    private VBox loadingScreen;

    private FontIcon loadingIcon;
    private Label applyingFiltersLabel;
    private Label filterIndexProgressLabel;
    private Label filterNameLabel;

    public FilterLoadingScreen() {
        super();

        FilterLoadingScreen.FILTER_LOADING_SCREEN = this;

        this.hide();

        this.setupContainer();
        this.setupElements();

        this.getChildren().addAll(this.loadingScreen);
    }

    private void setupContainer() {
        this.setAlignment(Pos.CENTER);
        this.getStyleClass().add("filter-loading-screen-container");
    }

    private void setupElements() {
        this.setupLoadingIcon();
        this.setupLabels();

        this.setupLoadingScreen();
    }

    private void setupLoadingIcon() {
        this.loadingIcon = new FontIcon(MaterialDesignL.LOADING);
        this.loadingIcon.setIconSize(128);
        this.loadingIcon.getStyleClass().add("filter-loading-screen-icon");

        var rotateTransition = new RotateTransition(Duration.millis(1000), this.loadingIcon);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(360);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.setCycleCount(Timeline.INDEFINITE);
        rotateTransition.play();
    }

    private void setupLabels() {
        this.applyingFiltersLabel = new Label("Applying filters...");
        this.applyingFiltersLabel.setPadding(new Insets(16, 0, 0, 0));
        this.applyingFiltersLabel.getStyleClass().add("filter-loading-screen-label");

        this.filterIndexProgressLabel = new Label("0 / 8");
        this.filterIndexProgressLabel.getStyleClass().add("filter-loading-screen-label");

        this.filterNameLabel = new Label("Gaussian Blur");
        this.filterNameLabel.getStyleClass().add(Styles.TEXT_SUBTLE);
    }

    private void setupLoadingScreen() {
        this.loadingScreen = new VBox(this.loadingIcon, this.applyingFiltersLabel, this.filterIndexProgressLabel, this.filterNameLabel);
        this.loadingScreen.setMaxWidth(VBox.USE_PREF_SIZE);
        this.loadingScreen.setAlignment(Pos.CENTER);
        this.loadingScreen.setPadding(new Insets(64));
        this.loadingScreen.getStyleClass().add("filter-loading-screen");
    }

    public void show() {
        this.setVisible(true);
    }

    public void hide() {
        this.setVisible(false);
    }

    public void updateFilterProgress(int filterIndex, int totalFilters, String filterName) {
        this.filterIndexProgressLabel.setText(String.format("%d / %d", filterIndex, totalFilters));
        this.filterNameLabel.setText(filterName);
    }
}
