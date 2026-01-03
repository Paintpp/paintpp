package com.purkynka.paintpp.ui.element.sidebar.filterlist;

import atlantafx.base.theme.Styles;
import com.purkynka.paintpp.logic.filter.FilterManager;
import com.purkynka.paintpp.logic.filter.imagefilter.ImageFilter;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignA;
import org.kordamp.ikonli.materialdesign2.MaterialDesignF;
import org.kordamp.ikonli.materialdesign2.MaterialDesignT;

public class FilterListRow extends HBox {
    private ImageFilter imageFilter;
    private int filterIndex;
    private int filterMaxIndex;

    private FontIcon rowIcon;
    private Label rowLabel;
    private Label indexLabel;

    private Button upButton;
    private Button downButton;
    private Button deleteButton;

    public FilterListRow(ImageFilter imageFilter, int filterIndex, int filterMaxIndex) {
        super(8);

        this.imageFilter = imageFilter;
        this.filterIndex = filterIndex;
        this.filterMaxIndex = filterMaxIndex;

        this.setAlignment(Pos.CENTER);

        this.setupElements();
        this.setupHandlers();

        var filler = new Pane();
        HBox.setHgrow(filler, Priority.ALWAYS);

        this.getChildren().addAll(
                this.rowIcon,
                this.rowLabel,
                this.indexLabel,
                filler,
                this.downButton,
                this.upButton,
                this.deleteButton
        );
    }

    private void setupElements() {
        this.setupInfoElements();
        this.setupButtons();
    }

    private void setupInfoElements() {
        this.rowIcon = new FontIcon(MaterialDesignF.FUNCTION_VARIANT);
        this.rowIcon.getStyleClass().add("filter-list-row-icon");
        this.rowIcon.setIconSize(24);

        this.rowLabel = new Label(this.imageFilter.getFilterType().getName());

        this.indexLabel = new Label("#" + (this.filterIndex + 1));
        this.indexLabel.getStyleClass().add(Styles.TEXT_SUBTLE);
    }

    private void setupButtons() {
        var upButtonIcon = new FontIcon(MaterialDesignA.ARROW_UP);
        upButtonIcon.getStyleClass().add("filter-list-row-icon");
        upButtonIcon.setIconSize(24);

        var downButtonIcon = new FontIcon(MaterialDesignA.ARROW_DOWN);
        downButtonIcon.getStyleClass().add("filter-list-row-icon");
        downButtonIcon.setIconSize(24);

        var deleteButtonIcon = new FontIcon(MaterialDesignT.TRASH_CAN);
        deleteButtonIcon.getStyleClass().add("filter-list-row-icon");
        deleteButtonIcon.setIconSize(24);

        this.upButton = new Button("", upButtonIcon);
        this.downButton = new Button("", downButtonIcon);
        this.deleteButton = new Button("", deleteButtonIcon);
    }

    private void setupHandlers() {
        this.upButton.setOnAction(_ -> this.moveUp());
        this.downButton.setOnAction(_ -> this.moveDown());
        this.deleteButton.setOnAction(_ -> this.delete());
    }

    private void moveUp() {
        if (this.filterIndex == this.filterMaxIndex) return;
        FilterManager.FILTERS.swap(this.filterIndex, this.filterIndex + 1);
    }

    private void moveDown() {
        if (this.filterIndex == 0) return;
        FilterManager.FILTERS.swap(this.filterIndex, this.filterIndex - 1);
    }

    private void delete() {
        FilterManager.FILTERS.remove(this.filterIndex);
    }
}
