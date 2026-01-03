package com.purkynka.paintpp.logic.filter.imagefilter;

import com.purkynka.paintpp.logic.filter.FilterType;
import com.purkynka.paintpp.logic.image.BufferBackedImage;

public abstract class ImageFilter {
    public abstract void modifyPixelBuffer(BufferBackedImage modifiedImage);
    public abstract FilterType getFilterType();
}
