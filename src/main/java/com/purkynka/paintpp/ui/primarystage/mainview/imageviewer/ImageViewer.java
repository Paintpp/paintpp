package com.purkynka.paintpp.ui.primarystage.mainview.imageviewer;

import com.purkynka.paintpp.logic.event.ConsumerEventHandler;
import com.purkynka.paintpp.logic.image.imageprovider.IImageProvider;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class ImageViewer extends StackPane {
    private IImageProvider imageProvider;

    private ImageView imageView;

    public static ConsumerEventHandler<IImageProvider> IMAGE_PROVIDER_CHANGED = new ConsumerEventHandler<>();

    public ImageViewer() {
        super();

        imageView = new ImageView();
        StackPane.setAlignment(imageView, Pos.CENTER);

        getChildren().addAll(imageView);

        IMAGE_PROVIDER_CHANGED.addEventListener(this::onImageProviderChanged);
    }

    private void onImageProviderChanged(IImageProvider imageProvider) {
        this.imageProvider = imageProvider;
        refreshImageView();
    }

    private void refreshImageView() {
        imageView.setImage(imageProvider.getImage());
    }
}
