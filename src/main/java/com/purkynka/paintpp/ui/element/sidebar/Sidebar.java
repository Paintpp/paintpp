package com.purkynka.paintpp.ui.element.sidebar;

import atlantafx.base.theme.Styles;
import com.purkynka.paintpp.logic.filter.FilterManager;
import com.purkynka.paintpp.ui.element.sidebar.filterlist.FilterList;
import com.purkynka.paintpp.ui.element.sidebar.imageswapper.ImageSwapperButtonContainer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignB;
import org.kordamp.ikonli.materialdesign2.MaterialDesignT;

public class Sidebar extends VBox {
    public static final double MIN_SIDEBAR_WIDTH = 420;
    public static final double MAX_SIDEBAR_WIDTH = 512;

    private ImageSwapperButtonContainer imageSwapperButtonContainer;
    private Button deleteAllFiltersButton;
    private FilterList filterList;

    public Sidebar() {
        super(8);

        this.setupContainer();
        this.setupElements();
        this.setupHandlers();

        var filler = new Pane();
        VBox.setVgrow(filler, Priority.ALWAYS);

        this.getChildren().addAll(this.imageSwapperButtonContainer, filler, this.deleteAllFiltersButton, this.filterList);
    }

    private void setupContainer() {
        this.getStyleClass().add("sidebar");
        this.setPadding(new Insets(16));
        this.setMinWidth(MIN_SIDEBAR_WIDTH);
        this.setMaxWidth(MAX_SIDEBAR_WIDTH);
        this.setFillWidth(true);
    }

    private void setupElements() {
        this.imageSwapperButtonContainer = new ImageSwapperButtonContainer();

        var deleteButtonIcon = new FontIcon(MaterialDesignT.TRASH_CAN);
        var deleteButtonText = new Label("Delete All Filters");
        var deleteButtonContent = new HBox(8, deleteButtonIcon, deleteButtonText);
        deleteButtonContent.setAlignment(Pos.CENTER);

        this.deleteAllFiltersButton = new Button("", deleteButtonContent);
        this.deleteAllFiltersButton.setMaxWidth(Double.MAX_VALUE);
        this.deleteAllFiltersButton.setMinHeight(48);
        this.deleteAllFiltersButton.getStyleClass().add(Styles.DANGER);
        this.deleteAllFiltersButton.setDisable(true);

        this.filterList = new FilterList(this);
    }

    private void setupHandlers() {
        FilterManager.FILTERS.addListener(filterList -> {
            this.deleteAllFiltersButton.setDisable(filterList.isEmpty());
        });

        this.deleteAllFiltersButton.setOnAction(_ -> {
            FilterManager.FILTERS.clear();
        });
    }
}
