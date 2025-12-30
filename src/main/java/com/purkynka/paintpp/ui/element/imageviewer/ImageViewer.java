package com.purkynka.paintpp.ui.element.imageviewer;


import atlantafx.base.theme.Styles;
import com.purkynka.paintpp.logic.event.ConsumerEvent;
import com.purkynka.paintpp.logic.event.ZoomChangeEvent;
import com.purkynka.paintpp.logic.image.ImageManager;
import com.purkynka.paintpp.logic.image.provider.ImageProvider;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ImageViewer extends StackPane {
    private static final double DEFAULT_MIN_ZOOM = 0.9d;
    private static final double MIN_ZOOM_LIMIT = 0.001d;
    private static final double DEFAULT_MAX_ZOOM = 5d;
    private static final double ZOOM_SENSITIVITY = 0.5d;

    private Label noImageLabel;

    private ImageView imageView;
    private Group imageViewGroup;

    private StackPane imagePaddingPane;
    private StackPane imageCenteringPane;

    private ScrollPane imageScrollPane;

    private boolean imageVisible;
    private ImageProvider imageProvider;

    private double currentZoom = ImageViewer.DEFAULT_MIN_ZOOM;
    private double currentMinZoom = ImageViewer.DEFAULT_MIN_ZOOM;
    private double currentMaxZoom = ImageViewer.DEFAULT_MAX_ZOOM;

    public static final ZoomChangeEvent ZOOM_CHANGE_EVENT = new ZoomChangeEvent();

    public ImageViewer() {
        super();

        VBox.setVgrow(this, Priority.ALWAYS);

        this.setupElements();
        this.setupListeners();

        this.getChildren().add(this.noImageLabel);
    }

    private void setupElements() {
        this.setupNoImageLabel();
        this.setupImageView();
        this.setupImagePaddingPane();
        this.setupImageCenteringPane();
        this.setupImageScrollPane();
        this.bindCenteringPaneWidth();
    }

    private void setupNoImageLabel() {
        this.noImageLabel = new Label("No image selected...");
        this.noImageLabel.getStyleClass().add(Styles.TEXT_SUBTLE);
        this.noImageLabel.setAlignment(Pos.CENTER);
    }

    private void setupImageView() {
        this.imageView = new ImageView();
        this.imageViewGroup = new Group(this.imageView);
    }

    private void setupImagePaddingPane() {
        this.imagePaddingPane = new StackPane(this.imageViewGroup);
        this.imagePaddingPane.setAlignment(Pos.CENTER);
    }

    private void setupImageCenteringPane() {
        this.imageCenteringPane = new StackPane(this.imagePaddingPane);
        this.imageCenteringPane.setAlignment(Pos.CENTER);
    }

    private void setupImageScrollPane() {
        this.imageScrollPane = new ScrollPane(this.imageCenteringPane);
        this.imageScrollPane.setPannable(true);

        this.imageScrollPane.prefWidthProperty().bind(this.widthProperty());
        this.imageScrollPane.prefHeightProperty().bind(this.heightProperty());
    }

    private void bindCenteringPaneWidth() {
        this.imageCenteringPane.prefWidthProperty().bind(this.imageScrollPane.widthProperty());
        this.imageCenteringPane.prefHeightProperty().bind(this.imageScrollPane.heightProperty());
    }

    private void setupListeners() {
        this.setupResizeListeners();
        this.setupImageProviderListener();
        this.setupZoomEventHandler();
    }

    private void setupResizeListeners() {
        this.imageScrollPane.widthProperty().addListener(_ -> this.onResize());
        this.imageScrollPane.heightProperty().addListener(_ -> this.onResize());
    }

    private void onResize() {
        this.recalculateZoomClampValues();
        this.recalculatePadding();
    }

    private void recalculateZoomClampValues() {
        var imageScrollPaneWidth = this.imageScrollPane.getPrefWidth();
        var imageScrollPaneHeight = this.imageScrollPane.getPrefHeight();

        var imageSize = this.imageProvider.getImageSize();

        var wantedMinZoomWidth = imageScrollPaneWidth / imageSize.width * ImageViewer.DEFAULT_MIN_ZOOM;
        var wantedMinZoomHeight = imageScrollPaneHeight / imageSize.height * ImageViewer.DEFAULT_MIN_ZOOM;

        var newMinZoom = Math.min(
                Math.max(
                        Math.min(wantedMinZoomWidth, wantedMinZoomHeight),
                        ImageViewer.MIN_ZOOM_LIMIT
                ),
                ImageViewer.DEFAULT_MIN_ZOOM
        );

        this.currentZoom = newMinZoom / (this.currentMinZoom / this.currentZoom);
        this.currentMinZoom = newMinZoom;

        var wantedMaxZoomWidth = imageScrollPaneWidth / imageSize.width * ImageViewer.DEFAULT_MAX_ZOOM;
        var wantedMaxZoomHeight = imageScrollPaneHeight / imageSize.height * ImageViewer.DEFAULT_MAX_ZOOM;

        this.currentMaxZoom = Math.max(wantedMaxZoomWidth, wantedMaxZoomHeight);

        ImageViewer.ZOOM_CHANGE_EVENT.invoke(this.currentZoom, this.currentMinZoom, this.currentMaxZoom);
    }

    private void recalculatePadding() {
        var topBottom = this.imageScrollPane.getPrefHeight() / 2;
        var leftRight = this.imageScrollPane.getPrefWidth() / 2;

        this.imagePaddingPane.setPadding(new Insets(topBottom, leftRight, topBottom, leftRight));
    }

    private void setupImageProviderListener() {
        ImageManager.IMAGE_PROVIDER.addUpdateListener(imageProvider -> {
            this.imageProvider = imageProvider;
            this.onImageProviderChanged();
        });
    }

    private void onImageProviderChanged() {
        var image = this.imageProvider.getImage();

        this.imageView.setImage(image);

        this.recalculateZoomClampValues();
        this.rescaleImageView();

        this.setScrollbarValues(0.5, 0.5);

        if (!this.imageVisible) {
            this.getChildren().setAll(this.imageScrollPane);
            this.imageVisible = true;
        }
    }

    private void rescaleImageView() {
        this.imageView.setScaleX(this.currentZoom);
        this.imageView.setScaleY(this.currentZoom);

        this.recalculatePadding();
    }

    private void setScrollbarValues(double newHorizontalValue, double newVerticalValue) {
        var clampedHorizontalValue = Math.clamp(newHorizontalValue, 0, 1);
        var clampedVerticalValue = Math.clamp(newVerticalValue, 0, 1);

        this.imageScrollPane.setHvalue(clampedHorizontalValue);
        this.imageScrollPane.setVvalue(clampedVerticalValue);
    }

    private void setupZoomEventHandler() {
        this.imageScrollPane.addEventFilter(ScrollEvent.SCROLL, e -> {
            if (!this.imageVisible || !e.isControlDown()) return;
            e.consume();

            var oldZoom = this.currentZoom;
            this.currentZoom = this.calculateNewZoom(e.getDeltaY());

            if (oldZoom == this.currentZoom) return;

            this.rescaleImageView();

            var scrollRatio = this.currentZoom / oldZoom;
            this.setScrollbarValues(
                    this.imageScrollPane.getHvalue() * scrollRatio,
                    this.imageScrollPane.getVvalue() * scrollRatio
            );

            ImageViewer.ZOOM_CHANGE_EVENT.invoke(this.currentZoom, this.currentMinZoom, this.currentMaxZoom);
        });
    }

    private double calculateNewZoom(double deltaY) {
        if (deltaY == 0) return this.currentZoom;

        var zoomMultiplier = deltaY > 0 ? ZOOM_SENSITIVITY : -ZOOM_SENSITIVITY;
        var newZoom = this.currentZoom * zoomMultiplier;

        return Math.clamp(this.currentZoom + newZoom, this.currentMinZoom, this.currentMaxZoom);
    }
}
