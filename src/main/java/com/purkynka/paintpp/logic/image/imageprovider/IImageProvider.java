package com.purkynka.paintpp.logic.image.imageprovider;

import com.purkynka.paintpp.logic.image.ImageSize;
import javafx.scene.image.Image;

/**
 * Common interface for all image providers.
 * <p>
 * The provided {@link Image} and {@link ImageSize} should be cached internally,
 * since large images can be resource intensive and recalculating them is costly.
 */
public interface IImageProvider {
    /**
     * Returns the provided {@link Image}, either from the cache or by generating it.
     * @return The provided image.
     */
    Image getImage();

    /**
     * Returns the {@link ImageSize} of the provided {@link Image}.
     * @return The image size of the provided image
     */
    ImageSize getImageSize();
}
