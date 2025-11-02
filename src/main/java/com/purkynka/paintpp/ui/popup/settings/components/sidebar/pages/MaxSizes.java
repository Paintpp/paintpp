package com.purkynka.paintpp.ui.popup.settings.components.sidebar.pages;

import com.purkynka.paintpp.logic.image.ImageSize;

public enum MaxSizes {
    Size2048(new ImageSize(2048, 2048)),
    Size4096(new ImageSize(4096, 4096)),
    Size6144(new ImageSize(6144, 6144)),
    Size8192(new ImageSize(8192, 8192)),
    Size12288(new ImageSize(12288, 12288)),
    Size16384(new ImageSize(16384, 16384));

    private final ImageSize imageSize;

    MaxSizes(ImageSize imageSize) {
        this.imageSize = imageSize;
    }

    public ImageSize getImageSize() {
        return imageSize;
    }

    public static MaxSizes getClosest(ImageSize target) {
        MaxSizes closest = null;
        long minDistance = Long.MAX_VALUE;

        for (MaxSizes sizeEnum : values()) {
            ImageSize imageSize = sizeEnum.getImageSize();
            long distance = Math.abs(imageSize.width() - target.width()) +
                    Math.abs(imageSize.height() - target.height());
            if (distance < minDistance) {
                minDistance = distance;
                closest = sizeEnum;
            }
        }

        return closest;
    }

    @Override
    public String toString() {
        return imageSize.toString();
    }
}
