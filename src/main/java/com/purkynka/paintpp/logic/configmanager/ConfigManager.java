package com.purkynka.paintpp.logic.configmanager;

import com.purkynka.paintpp.logic.event.ConsumerEventHandler;
import com.purkynka.paintpp.logic.image.ImageGenerationType;
import com.purkynka.paintpp.logic.image.ImageSize;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ConfigManager {
    public static final ConsumerEventHandler<Integer> FONT_SIZE_CHANGED = new ConsumerEventHandler<>();
    public static final ConsumerEventHandler<ImageSize> MAX_IMAGE_SIZE_CHANGED = new ConsumerEventHandler<>();

    private static ImageSize maxImageSize = new ImageSize(4096, 4096);
    private static boolean rememberImageSize = true;
    private static ImageSize rememberedImageSize = new ImageSize(400, 400);
    private static ImageSize preferredImageSize = new ImageSize(400, 400);

    private static boolean rememberBackgroundColor = true;
    private static Color rememberedBackgroundColor = new Color(1, 1, 1, 1);
    private static Color preferredBackgroundColor = new Color(1, 1, 1, 1);

    private static boolean rememberGenerationType = true;
    private static ImageGenerationType rememberedGenerationType = ImageGenerationType.XCoordinate;
    private static ImageGenerationType preferredGenerationType = ImageGenerationType.XCoordinate;

    private static Themes theme = Themes.CupertinoDark;
    private static Fonts font = Fonts.System;
    private static int fontSize = 13;

    // General
    public static boolean isRememberImageSize() {
        return rememberImageSize;
    }

    public static void setRememberImageSize(boolean rememberImageSize) {
        ConfigManager.rememberImageSize = rememberImageSize;
    }

    public static ImageSize getRememberedImageSize() {
        return rememberedImageSize;
    }

    public static void setRememberedImageSize(ImageSize rememberedImageSize) {
        ConfigManager.rememberedImageSize = rememberedImageSize;
    }

    public static void setRememberedImageWidth(int width) {
        rememberedImageSize = new ImageSize(width, rememberedImageSize.height());
    }

    public static void setRememberedImageHeight(int height) {
        rememberedImageSize = new ImageSize(rememberedImageSize.width(), height);
    }

    public static ImageSize getPreferredImageSize() {
        return preferredImageSize;
    }

    public static void setPreferredImageWidth(int width) {
        preferredImageSize = new ImageSize(width, preferredImageSize.height());
    }

    public static void setPreferredImageHeight(int height) {
        preferredImageSize = new ImageSize(preferredImageSize.width(), height);
    }

    public static void setPreferredImageSize(ImageSize preferredImageSize) {
        ConfigManager.preferredImageSize = preferredImageSize;
    }

    public static ImageSize getMaxImageSize() {
        return maxImageSize;
    }

    public static void setMaxImageSize(ImageSize size) {
        maxImageSize = size;
        MAX_IMAGE_SIZE_CHANGED.send(size);
    }

    public static boolean isRememberBackgroundColor() {
        return rememberBackgroundColor;
    }

    public static void setRememberBackgroundColor(boolean rememberBackgroundColor) {
        ConfigManager.rememberBackgroundColor = rememberBackgroundColor;
    }

    public static Color getRememberedBackgroundColor() {
        return rememberedBackgroundColor;
    }

    public static void setRememberedBackgroundColor(Color rememberedBackgroundColor) {
        ConfigManager.rememberedBackgroundColor = rememberedBackgroundColor;
    }

    public static Color getPreferredBackgroundColor() {
        return preferredBackgroundColor;
    }

    public static void setPreferredBackgroundColor(Color preferredBackgroundColor) {
        ConfigManager.preferredBackgroundColor = preferredBackgroundColor;
    }

    public static boolean isRememberGenerationType() {
        return rememberGenerationType;
    }

    public static void setRememberGenerationType(boolean rememberGenerationType) {
        ConfigManager.rememberGenerationType = rememberGenerationType;
    }

    public static ImageGenerationType getRememberedGenerationType() {
        return rememberedGenerationType;
    }

    public static void setRememberedGenerationType(ImageGenerationType rememberedGenerationType) {
        ConfigManager.rememberedGenerationType = rememberedGenerationType;
    }

    public static ImageGenerationType getPreferredGenerationType() {
        return preferredGenerationType;
    }

    public static void setPreferredGenerationType(ImageGenerationType preferredGenerationType) {
        ConfigManager.preferredGenerationType = preferredGenerationType;
    }

    // Appearance
    public static Themes getTheme() {
        return theme;
    }

    public static void setTheme(Themes theme) {
        ConfigManager.theme = theme;
        Application.setUserAgentStylesheet(theme.getUserAgentStylesheet());
    }

    public static Fonts getFont() {
        return font;
    }

    public static void setFont(Fonts newFont) {
        font = newFont;
        applyFont();
    }

    public static int getFontSize() {
        return fontSize;
    }

    public static void setFontSize(int fontSize) {
        ConfigManager.fontSize = fontSize;
        FONT_SIZE_CHANGED.send(fontSize);
        applyFont();
    }

    public static void applyFont() {
        for (Window window : Window.getWindows()) {
            if (window instanceof Stage stage && stage.getScene() != null) {
                stage.getScene().getRoot().setStyle(
                        "-fx-font-family: '" + font + "';" +
                                "-fx-font-size: " + fontSize + ";"
                );
            }
        }
    }

    public static void applyFont(Stage stage) {
        stage.getScene().getRoot().setStyle(
                "-fx-font-family: '" + font + "';" +
                        "-fx-font-size: " + fontSize + ";"
        );
    }
}
