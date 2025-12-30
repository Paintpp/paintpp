package com.purkynka.paintpp.ui.element.statusbar;

import com.purkynka.paintpp.logic.image.ImageManager;
import com.purkynka.paintpp.ui.CommonCSS;
import com.purkynka.paintpp.ui.element.imageviewer.ImageViewer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignA;
import org.kordamp.ikonli.materialdesign2.MaterialDesignM;

public class StatusBar extends HBox {
    public static final double STATUS_BAR_HEIGHT = 32d;
    private static final double ACTUAL_STATUS_BAR_WIDTH = STATUS_BAR_HEIGHT + CommonCSS.BORDER_WIDTH;

    private HBox imageSizeWatcher;
    private Label imageSizeWatcherWidth;
    private Label imageSizeWatcherHeight;

    private HBox imageZoomWatcher;
    private Label imageZoom;

    public StatusBar() {
        super();

        this.getStyleClass().add("status-bar");

        this.setMinHeight(ACTUAL_STATUS_BAR_WIDTH);
        this.setMaxHeight(ACTUAL_STATUS_BAR_WIDTH);

        this.setupElements();
        this.setupChangeHandlers();

        var filler = new Pane();
        HBox.setHgrow(filler, Priority.ALWAYS);

        this.getChildren().addAll(
                this.imageSizeWatcher,
                filler,
                this.imageZoomWatcher
        );
    }

    private void setupElements() {
        this.setupImageSizeWatcher();
        this.setupImageZoomWatcher();
    }

    private void setupImageSizeWatcher() {
        var sizeIcon = new FontIcon(MaterialDesignA.ARROW_EXPAND);
        sizeIcon.getStyleClass().add("image-size-watcher-icon");

        this.imageSizeWatcherWidth = new Label("W");
        this.imageSizeWatcherWidth.setMinWidth(48);
        this.imageSizeWatcherWidth.getStyleClass().addAll("image-size-watcher-label", "image-size-watcher-size");

        var by = new Label("×");
        by.getStyleClass().add("image-size-watcher-label");

        this.imageSizeWatcherHeight = new Label("H");
        this.imageSizeWatcherHeight.setMinWidth(48);
        this.imageSizeWatcherHeight.getStyleClass().addAll("image-size-watcher-label", "image-size-watcher-size");

        this.imageSizeWatcher = new HBox(sizeIcon, this.imageSizeWatcherWidth, by, this.imageSizeWatcherHeight);
        this.imageSizeWatcher.setPadding(new Insets(0, 4, 0, 8));
        this.imageSizeWatcher.setAlignment(Pos.CENTER);
        this.imageSizeWatcher.getStyleClass().add("image-size-watcher");
    }

    private void setupImageZoomWatcher() {
        var zoomIcon = new FontIcon(MaterialDesignM.MAGNIFY);
        zoomIcon.getStyleClass().add("image-zoom-watcher-icon");

        this.imageZoom = new Label("1x");
        this.imageZoom.setMinWidth(48);
        this.imageZoom.getStyleClass().addAll("image-zoom-watcher-label", "image-zoom-watcher-zoom");

        this.imageZoomWatcher = new HBox(zoomIcon, this.imageZoom);
        this.imageZoomWatcher.setPadding(new Insets(0, 4, 0, 8));
        this.imageZoomWatcher.setAlignment(Pos.CENTER);
        this.imageZoomWatcher.getStyleClass().add("image-zoom-watcher");
    }

    private void setupChangeHandlers() {
        ImageManager.IMAGE_PROVIDER.addUpdateListener(imageProvider -> {
            var imageSize = imageProvider.getImageSize();

            this.imageSizeWatcherWidth.setText(imageSize.width.toString());
            this.imageSizeWatcherHeight.setText(imageSize.height.toString());
        });

        ImageViewer.ZOOM_CHANGE_EVENT.addEventListener(
                (currentZoom, _, _) -> this.imageZoom.setText(String.format("%.2fx", currentZoom))
        );
    }
}
