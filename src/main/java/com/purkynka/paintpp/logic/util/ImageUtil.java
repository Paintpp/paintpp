package com.purkynka.paintpp.logic.util;

import com.purkynka.paintpp.logic.filter.FilterManager;
import com.purkynka.paintpp.logic.image.BufferBackedImage;
import com.purkynka.paintpp.logic.image.ImageManager;

public class ImageUtil {
    public static BufferBackedImage getCurrentImage() {
        if (ImageManager.DISPLAYING_MODIFIED_IMAGE.get()) return FilterManager.FILTERED_IMAGE.get();
        else return ImageManager.IMAGE_PROVIDER.get().getBufferBackedImage();
    }
}
