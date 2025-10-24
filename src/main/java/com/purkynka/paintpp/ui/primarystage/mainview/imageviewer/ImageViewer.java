package com.purkynka.paintpp.ui.primarystage.mainview.imageviewer;

import com.purkynka.paintpp.logic.image.imageprovider.IImageProvider;
import com.purkynka.paintpp.ui.shared.TemporaryPane;

public class ImageViewer extends TemporaryPane {
    public static IImageProvider CURRENT_IMAGE_PROVIDER;

    public ImageViewer() {
        super("Image Viewer");
    }
}
