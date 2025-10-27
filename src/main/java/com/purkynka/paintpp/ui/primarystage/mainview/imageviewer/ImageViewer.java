package com.purkynka.paintpp.ui.primarystage.mainview.imageviewer;

import atlantafx.base.theme.Styles;
import com.purkynka.paintpp.logic.event.ConsumerEventHandler;
import com.purkynka.paintpp.logic.image.imageprovider.IImageProvider;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;

public class ImageViewer extends StackPane {
    public static ConsumerEventHandler<IImageProvider> IMAGE_PROVIDER_CHANGED = new ConsumerEventHandler<>();

    private static final double DEFAULT_MIN_ZOOM = 0.9d;
    private static final double MIN_ZOOM_LIMIT = 0.001d;
    private static final double DEFAULT_MAX_ZOOM = 5d;
    private static final double ZOOM_SENSITIVITY = 0.05d;

    private IImageProvider imageProvider;

    private Label noImageLabel;
    private ScrollPane imageScrollPane;
    private StackPane imageCenterPane;
    private StackPane imagePaddingPane;
    private Canvas imageCanvas;

    private boolean imageVisible = false;

    private double currentMinZoom = DEFAULT_MIN_ZOOM;
    private double currentMaxZoom = DEFAULT_MAX_ZOOM;
    private double currentZoom = DEFAULT_MIN_ZOOM;

    public ImageViewer() {
        super();

        setupNodes();
        setupImageEventHandler();
        setupZoomEventHandler();

        getChildren().setAll(noImageLabel);
    }

    private void setupNodes() {
        noImageLabel = new Label("No image selected...");
        noImageLabel.getStyleClass().add(Styles.TEXT_SUBTLE);
        noImageLabel.setAlignment(Pos.CENTER);

        imageCanvas = new Canvas();
        Group imageGroup = new Group(imageCanvas);

        imagePaddingPane = new StackPane(imageGroup);
        imagePaddingPane.setAlignment(Pos.CENTER);

        imageCenterPane = new StackPane(imagePaddingPane);
        imageCenterPane.setAlignment(Pos.CENTER);

        setupImageScrollPane();

        imageCenterPane.prefWidthProperty().bind(imageScrollPane.widthProperty());
        imageCenterPane.prefHeightProperty().bind(imageScrollPane.heightProperty());
    }

    private void setupImageScrollPane() {
        imageScrollPane = new ScrollPane(imageCenterPane);
        imageScrollPane.setPannable(true);

        imageScrollPane.prefWidthProperty().bind(widthProperty());
        imageScrollPane.prefHeightProperty().bind(heightProperty());

        imageScrollPane.widthProperty().addListener(_ -> onScrollPaneResize());
        imageScrollPane.heightProperty().addListener(_ -> onScrollPaneResize());
    }

    private void onScrollPaneResize() {
        recalculateZoomClampValues();
        recalculatePadding();
    }

    private void recalculatePadding() {
        var topBottom = imageScrollPane.getPrefHeight() / 2;
        var leftRight = imageScrollPane.getPrefWidth() / 2;

        imagePaddingPane.setPadding(new Insets(topBottom, leftRight, topBottom, leftRight));
    }

    private void setScrollbarValues(double newHorizontalValue, double newVerticalValue) {
        var clampedHorizontalValue = Math.clamp(newHorizontalValue, 0, 1);
        var clampedVerticalValue = Math.clamp(newVerticalValue, 0, 1);

        imageScrollPane.setHvalue(clampedHorizontalValue);
        imageScrollPane.setVvalue(clampedVerticalValue);
    }

    private void setupImageEventHandler() {
        IMAGE_PROVIDER_CHANGED.addEventListener(this::onImageProviderChanged);
    }

    private void onImageProviderChanged(IImageProvider imageProvider) {
        this.imageProvider = imageProvider;

        var image = imageProvider.getImage();
        var imageSize = imageProvider.getImageSize();

        imageCanvas.setWidth(imageSize.widthAsDouble());
        imageCanvas.setHeight(imageSize.heightAsDouble());

        recalculateZoomClampValues();
        rescaleCanvas();

        var canvasContext = imageCanvas.getGraphicsContext2D();
        canvasContext.clearRect(0, 0, imageSize.widthAsDouble(), imageSize.heightAsDouble());
        canvasContext.drawImage(image, 0, 0);

        Platform.runLater(() -> Platform.runLater(() -> setScrollbarValues(0.5, 0.5)));

        if (!imageVisible) {
            getChildren().setAll(imageScrollPane);
            imageVisible = true;
        }
    }

    private void recalculateZoomClampValues() {
        var imageScrollPaneWidth = imageScrollPane.getPrefWidth();
        var imageScrollPaneHeight = imageScrollPane.getPrefHeight();

        var imageSize = imageProvider.getImageSize();

        var wantedMinZoomWidth = imageScrollPaneWidth / imageSize.widthAsDouble() * DEFAULT_MIN_ZOOM;
        var wantedMinZoomHeight = imageScrollPaneHeight / imageSize.heightAsDouble() * DEFAULT_MIN_ZOOM;

        var newMinZoom = Math.min(
                Math.max(
                        Math.min(wantedMinZoomWidth, wantedMinZoomHeight),
                        MIN_ZOOM_LIMIT
                ),
                DEFAULT_MIN_ZOOM
        );

        currentZoom = newMinZoom / (currentMinZoom / currentZoom);
        currentMinZoom = newMinZoom;

        var wantedMaxZoomWidth = imageScrollPaneWidth / imageSize.widthAsDouble() * DEFAULT_MAX_ZOOM;
        var wantedMaxZoomHeight = imageScrollPaneHeight / imageSize.heightAsDouble() * DEFAULT_MAX_ZOOM;

        currentMaxZoom = Math.max(wantedMaxZoomWidth, wantedMaxZoomHeight);
    }

    private void setupZoomEventHandler() {
        imageScrollPane.addEventFilter(ScrollEvent.SCROLL, e -> {
            if (!imageVisible || !e.isControlDown()) return;

            var oldZoom = currentZoom;
            currentZoom = calculateNewZoom(e.getDeltaY());

            if (oldZoom == currentZoom) {
                e.consume();
                return;
            }

            rescaleCanvas();

            var scrollRatio = currentZoom / oldZoom;
            setScrollbarValues(
                    imageScrollPane.getHvalue() * scrollRatio,
                    imageScrollPane.getVvalue() * scrollRatio
            );

            e.consume();
        });
    }

    private double calculateNewZoom(double scrollDelta) {
        if (scrollDelta == 0) return currentZoom;

        var zoomMultiplier = scrollDelta > 0 ? ZOOM_SENSITIVITY : -ZOOM_SENSITIVITY;
        var zoomChange = currentZoom * zoomMultiplier;

        return Math.clamp(currentZoom + zoomChange, currentMinZoom, currentMaxZoom);
    }

    private void rescaleCanvas() {
        imageCanvas.setScaleX(currentZoom);
        imageCanvas.setScaleY(currentZoom);

        recalculatePadding();
    }
}
