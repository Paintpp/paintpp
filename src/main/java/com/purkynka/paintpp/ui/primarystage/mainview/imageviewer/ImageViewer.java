package com.purkynka.paintpp.ui.primarystage.mainview.imageviewer;

import com.purkynka.paintpp.logic.event.ConsumerEventHandler;
import com.purkynka.paintpp.logic.image.imageprovider.IImageProvider;
import com.purkynka.paintpp.ui.shared.TemporaryPane;

public class ImageViewer extends TemporaryPane {
    private IImageProvider imageProvider;

    public static ConsumerEventHandler<IImageProvider> IMAGE_PROVIDER_CHANGED = new ConsumerEventHandler<>();

    public ImageViewer() {
        super("Image Viewer");

        IMAGE_PROVIDER_CHANGED.addEventListener(this::onImageProviderChanged);
    }

    private void onImageProviderChanged(IImageProvider imageProvider) {
        this.imageProvider = imageProvider;
        refreshImageView();
    }

    private void refreshImageView() {}
}
