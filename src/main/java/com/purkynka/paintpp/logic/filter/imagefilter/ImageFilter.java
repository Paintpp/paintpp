package com.purkynka.paintpp.logic.filter.imagefilter;

import com.purkynka.paintpp.logic.filter.FilterType;
import com.purkynka.paintpp.logic.image.BufferBackedImage;

public abstract class ImageFilter {
    private BufferBackedImage cachedImage = null;
    private boolean dirty;

    private long calculationTime;
    private long copyTime;

    public boolean copyCacheOrModifyPixelBuffer(BufferBackedImage image) {
        var before = System.nanoTime();

        if (cachedImage == null || dirty) {
            this.modifyPixelBuffer(image);
            this.cachedImage = new BufferBackedImage(image);
            this.dirty = false;

            this.calculationTime = System.nanoTime() - before;

            return false;
        } else {
            var imageSize = this.cachedImage.getImageSize();
            var cachedIntBuffer = this.cachedImage.getPixelIntBuffer();
            var intBuffer = image.getPixelIntBuffer();

            intBuffer.put(0, cachedIntBuffer, 0, imageSize.totalPixels());

            this.copyTime = System.nanoTime() - before;

            return true;
        }
    }

    public void markDirty() {
        this.dirty = true;
    }

    public long getCalculationTime() {
        return calculationTime;
    }

    public long getCopyTime() {
        return copyTime;
    }

    public abstract void modifyPixelBuffer(BufferBackedImage modifiedImage);

    public abstract FilterType getFilterType();
}
