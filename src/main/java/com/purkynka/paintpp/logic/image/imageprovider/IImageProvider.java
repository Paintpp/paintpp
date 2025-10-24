package com.purkynka.paintpp.logic.image.imageprovider;

import com.purkynka.paintpp.logic.image.ImageSize;
import javafx.scene.image.Image;

public interface IImageProvider {
    Image getImage();
    ImageSize getImageSize();
}
