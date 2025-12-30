package com.purkynka.paintpp.logic.image.provider;

import com.purkynka.paintpp.logic.size.IntSize;
import javafx.scene.image.Image;

public interface ImageProvider {
    Image getImage();
    IntSize getImageSize();
}
