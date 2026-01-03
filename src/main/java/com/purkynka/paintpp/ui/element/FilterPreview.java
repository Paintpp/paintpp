package com.purkynka.paintpp.ui.element;

import atlantafx.base.theme.Styles;
import com.purkynka.paintpp.logic.filter.imagefilter.ImageFilter;
import com.purkynka.paintpp.logic.image.BufferBackedImage;
import com.purkynka.paintpp.logic.util.ImageUtil;
import javafx.geometry.Pos;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class FilterPreview extends VBox {
    private BufferBackedImage scaledImageCopy;

    private final ImageFilter imageFilter;

    private ImageView imageView;
    private Label previewLabel;

    public FilterPreview(ImageFilter imageFilter) {
        super(16);

        this.imageFilter = imageFilter;

        this.setupImageView();
        this.setupScaledImage();
        this.setupPreviewLabel();

        this.refreshImage();

        this.setAlignment(Pos.CENTER);

        this.getChildren().addAll(this.imageView, this.previewLabel);
    }

    private void setupScaledImage() {
        var currentImage = ImageUtil.getCurrentImage();
        this.imageView.setImage(currentImage.getImage());

        var snapshotParameters = new SnapshotParameters();
        snapshotParameters.setFill(Color.TRANSPARENT);

        var scaledImage = this.imageView.snapshot(snapshotParameters, null);
        this.scaledImageCopy = new BufferBackedImage(scaledImage);

        this.imageView.setImage(scaledImage);
    }

    private void setupImageView() {
        this.imageView = new ImageView();
        this.imageView.setFitWidth(256);
        this.imageView.setFitHeight(256);
        this.imageView.setPreserveRatio(true);
    }

    private void setupPreviewLabel() {
        var imageSize = this.scaledImageCopy.getImageSize();
        this.previewLabel = new Label(String.format("Preview (%d x %d)", imageSize.width, imageSize.height));
        this.previewLabel.getStyleClass().add(Styles.TEXT_SUBTLE);
    }

    public void refreshImage() {
        var imageCopy = new BufferBackedImage(this.scaledImageCopy);
        this.imageFilter.modifyPixelBuffer(imageCopy);
        this.imageView.setImage(imageCopy.getImage());
    }
}
