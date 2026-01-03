package com.purkynka.paintpp.ui.element.sidebar.filterlist;

import atlantafx.base.theme.Styles;
import com.purkynka.paintpp.logic.filter.FilterManager;
import com.purkynka.paintpp.logic.filter.imagefilter.ImageFilter;
import com.purkynka.paintpp.ui.element.sidebar.Sidebar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class FilterList extends ScrollPane {
    private StackPane emptyFilterListPane;

    private VBox filterContainer;

    public FilterList(Sidebar sidebar) {
        super();

        this.setupContainer(sidebar);
        this.setupElements();
        this.setupHandlers();

        this.hideFilterContainer();
    }

    private void setupContainer(Sidebar sidebar) {
        // this.prefWidthProperty().bind(sidebar.widthProperty().subtract(32));
        this.setPrefHeight(256);
        this.setHbarPolicy(ScrollBarPolicy.NEVER);
        this.getStyleClass().add("filter-list");

        var clipRectangle = new Rectangle();
        clipRectangle.widthProperty().bind(this.widthProperty());
        clipRectangle.setHeight(256);
        clipRectangle.setArcHeight(16);
        clipRectangle.setArcWidth(16);

        this.setClip(clipRectangle);
    }

    private void setupElements() {
        this.setupEmptyFilterListPane();
        this.setupFilterContainer();
    }

    private void setupEmptyFilterListPane() {
        this.emptyFilterListPane = new StackPane();
        this.emptyFilterListPane.minWidthProperty().bind(this.widthProperty());
        this.emptyFilterListPane.minHeightProperty().bind(this.heightProperty());

        var label = new Label("No filters applied...");
        label.getStyleClass().add(Styles.TEXT_SUBTLE);
        label.setAlignment(Pos.CENTER);
        this.emptyFilterListPane.getChildren().add(label);
    }

    private void setupFilterContainer() {
        this.filterContainer = new VBox(8);
        this.filterContainer.setFillWidth(true);
        this.filterContainer.setPadding(new Insets(8));
        this.filterContainer.minWidthProperty().bind(this.widthProperty().subtract(8));
    }

    private void setupHandlers() {
        FilterManager.FILTERS.addListener(filters -> {
            if (filters.isEmpty()) this.hideFilterContainer();
            else {
                this.refreshFilterContainer(filters);
                this.showFilterContainer();
            }
        });
    }

    private void hideFilterContainer() {
        this.setVbarPolicy(ScrollBarPolicy.NEVER);
        this.setContent(this.emptyFilterListPane);
    }

    private void showFilterContainer() {
        this.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        this.setContent(this.filterContainer);
    }

    private void refreshFilterContainer(ArrayList<ImageFilter> filters) {
        var filterContainerChildren = this.filterContainer.getChildren();

        filterContainerChildren.clear();

        var maxIndex = filters.size() - 1;
        for (int i = maxIndex; i >= 0; i--) {
            var filter = filters.get(i);
            filterContainerChildren.add(new FilterListRow(filter, i, maxIndex));
        }
    }
}
