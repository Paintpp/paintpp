package com.purkynka.paintpp.logic.util;

import com.purkynka.paintpp.ui.stage.main.MainStage;

import java.util.Objects;

public class ResourceUtil {
    public static String getResource(String relativePath) {
        return Objects.requireNonNull(MainStage.class.getResource("/com/purkynka/paintpp" + relativePath)).toExternalForm();
    }
}
