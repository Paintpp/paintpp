package com.purkynka.paintpp.logic.filter;

import com.purkynka.paintpp.logic.filter.imagefilter.ImageFilter;
import com.purkynka.paintpp.logic.image.BufferBackedImage;
import com.purkynka.paintpp.logic.image.ImageManager;
import com.purkynka.paintpp.logic.observable.ObservableArrayList;
import com.purkynka.paintpp.logic.observable.ObservableValue;

public class FilterManager {
    public static ObservableArrayList<ImageFilter> FILTERS = new ObservableArrayList<>();
    public static ObservableValue<BufferBackedImage> FILTERED_IMAGE = new ObservableValue<>();

    static {
        ImageManager.IMAGE_PROVIDER.addUpdateListener(
                imageProvider -> {
                    FilterManager.FILTERS.clear();
                    applyFilters(imageProvider.getBufferBackedImage());
                }
        );

        FilterManager.FILTERS.addListener(
                _ -> applyFilters(ImageManager.IMAGE_PROVIDER.get().getBufferBackedImage())
        );
    }

    private static void applyFilters(BufferBackedImage originalImage) {
        var newImage = new BufferBackedImage(originalImage);

        for (ImageFilter filter : FilterManager.FILTERS) {
            filter.modifyPixelBuffer(newImage);
        }

        FILTERED_IMAGE.set(newImage);
    }
}
