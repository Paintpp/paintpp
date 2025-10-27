package com.purkynka.paintpp.ui.primarystage.mainview.imageviewer;

import atlantafx.base.theme.Styles;
import com.purkynka.paintpp.logic.event.ConsumerEventHandler;
import com.purkynka.paintpp.logic.image.imageprovider.IImageProvider;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;

/**
 * {@link StackPane} containing the actual rendered {@link Image}.
 * <p>
 * Supports panning and zooming, the image is automatically centered and padded
 * to allow panning to the edge properly.
 */
public class ImageViewer extends StackPane {
    public static final ConsumerEventHandler<IImageProvider> IMAGE_PROVIDER_CHANGED = new ConsumerEventHandler<>();

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

    /**
     * Constructs a new {@link ImageViewer}.
     */
    public ImageViewer() {
        super();

        setupNodes();
        setupImageEventHandler();
        setupZoomEventHandler();

        getChildren().setAll(noImageLabel);
    }

    /**
     * Sets up the elements required to display an {@link Image} from an {@link IImageProvider}.
     */
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

    /**
     * Sets up the main {@link #imageScrollPane}.
     */
    private void setupImageScrollPane() {
        imageScrollPane = new ScrollPane(imageCenterPane);
        imageScrollPane.setPannable(true);

        imageScrollPane.prefWidthProperty().bind(widthProperty());
        imageScrollPane.prefHeightProperty().bind(heightProperty());

        imageScrollPane.widthProperty().addListener(_ -> onScrollPaneResize());
        imageScrollPane.heightProperty().addListener(_ -> onScrollPaneResize());
    }

    /**
     * Runs whenever the main {@link #imageScrollPane} resizes.
     */
    private void onScrollPaneResize() {
        recalculateZoomClampValues();
        recalculatePadding();
    }

    /**
     * Recalculates the {@link #imagePaddingPane} padding based on the current {@link #imageScrollPane} preferred size.
     */
    private void recalculatePadding() {
        var topBottom = imageScrollPane.getPrefHeight() / 2;
        var leftRight = imageScrollPane.getPrefWidth() / 2;

        imagePaddingPane.setPadding(new Insets(topBottom, leftRight, topBottom, leftRight));
    }

    /**
     * Sets the {@link #imageScrollPane} scrollbar value, clamping them between {@code 0} and {@code 1}.
     * @param newHorizontalValue The new horizontal scrollbar value.
     * @param newVerticalValue The new vertical scrollbar value.
     */
    private void setScrollbarValues(double newHorizontalValue, double newVerticalValue) {
        var clampedHorizontalValue = Math.clamp(newHorizontalValue, 0, 1);
        var clampedVerticalValue = Math.clamp(newVerticalValue, 0, 1);

        imageScrollPane.setHvalue(clampedHorizontalValue);
        imageScrollPane.setVvalue(clampedVerticalValue);
    }

    /**
     * Sets up the event listener for the {@link #IMAGE_PROVIDER_CHANGED} event.
     */
    private void setupImageEventHandler() {
        IMAGE_PROVIDER_CHANGED.addEventListener(this::onImageProviderChanged);
    }

    /**
     * Runs whenever the {@link #IMAGE_PROVIDER_CHANGED} event fires,
     * providing a new {@link IImageProvider} to use.
     * @param imageProvider The image provider to use.
     */
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

        // Disgusting hack to fix the scrollbar's not resetting when
        // a different image is loaded in.
        Platform.runLater(() -> Platform.runLater(() -> setScrollbarValues(0.5, 0.5)));

        if (!imageVisible) {
            getChildren().setAll(imageScrollPane);
            imageVisible = true;
        }
    }

    /**
     * Recalculates the {@link #currentMinZoom} and {@link #currentMaxZoom} values, to better
     * accommodate the current image.
     * <p>
     * Also resizes the {@link #currentZoom} to scale with the new minimum and maximum zoom.
     */
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

    /**
     * Sets up an {@link EventHandler} acting as an {@code EventFilter} for {@link ScrollEvent ScrollEvents} on the {@link #imageScrollPane},
     * to listen for {@code CTRL + Mouse Wheel} events and zoom in or out.
     */
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

    /**
     * Calculates a new zoom value based on the provided scroll delta.
     * @param scrollDelta The scroll delta from a {@link ScrollEvent}
     * @return The calculated zoom value, clamped between {@link #currentMinZoom} and {@link #currentMaxZoom}
     */
    private double calculateNewZoom(double scrollDelta) {
        if (scrollDelta == 0) return currentZoom;

        var zoomMultiplier = scrollDelta > 0 ? ZOOM_SENSITIVITY : -ZOOM_SENSITIVITY;
        var zoomChange = currentZoom * zoomMultiplier;

        return Math.clamp(currentZoom + zoomChange, currentMinZoom, currentMaxZoom);
    }

    /**
     * Rescales the {@link #imageCanvas} based on the {@link #currentZoom} value,
     * and recalculates the padding of the {@link #imagePaddingPane}.
     */
    private void rescaleCanvas() {
        imageCanvas.setScaleX(currentZoom);
        imageCanvas.setScaleY(currentZoom);

        recalculatePadding();
    }
}
