package com.purkynka.paintpp.logic.image.provider;

import com.purkynka.paintpp.logic.image.BufferBackedImage;
import com.purkynka.paintpp.logic.size.IntSize;
import javafx.scene.image.Image;

import java.nio.IntBuffer;

public interface ImageProvider {
    BufferBackedImage getBufferBackedImage();
}
