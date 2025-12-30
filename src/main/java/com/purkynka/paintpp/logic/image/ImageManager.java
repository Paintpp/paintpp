package com.purkynka.paintpp.logic.image;

import com.purkynka.paintpp.logic.image.provider.ImageProvider;
import com.purkynka.paintpp.logic.observable.ObservableValue;

public class ImageManager {
    public static ObservableValue<ImageProvider> IMAGE_PROVIDER = new ObservableValue<>();

    public static ObservableValue<Boolean> DISPLAYING_MODIFIED_IMAGE = new ObservableValue<>(true);
}
