package com.purkynka.paintpp.logic.image.imageprovider;

import com.purkynka.paintpp.logic.image.ImageGenerationType;
import com.purkynka.paintpp.logic.image.ImageSize;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * {@link IImageProvider} for an {@link Image} created with some algorithm.
 * <p>
 * Contains several options, represented by the {@link ImageGenerationType} enum.
 */
public class GeneratedImageProvider implements IImageProvider {
    private static final int MANDELBROT_ITERATIONS = 1000;

    private ImageSize imageSize;
    private Image image;

    /**
     * Constructs a {@link GeneratedImageProvider}, creating an {@link Image} based on the provided
     * {@link ImageSize} and {@link ImageGenerationType}.
     *
     * @param imageSize The image size of the generated image
     * @param imageGenerationType The image generation type to use for generating the image
     */
    public GeneratedImageProvider(ImageSize imageSize, ImageGenerationType imageGenerationType) {
        this.imageSize = imageSize;

        var writableImage = new WritableImage(imageSize.width(), imageSize.height());
        var pixelWriter = writableImage.getPixelWriter();

        switch (imageGenerationType) {
            case XCoordinate -> xCoordinateGenerator(pixelWriter);
            case YCoordinate -> yCoordinateGenerator(pixelWriter);
            case XYCoordinates -> xyCoordinatesGenerator(pixelWriter);
            case XYCoordinatesAverage -> xyCoordinatesAverageGenerator(pixelWriter);
            case Sin -> sinGenerator(pixelWriter);
            case Circle -> circleGenerator(pixelWriter);
            case CircleFalloff -> circleFalloffGenerator(pixelWriter);
            case Mandelbrot -> mandelbrotGenerator(pixelWriter);
        }

        image = writableImage;
    }

    /**
     * Generates an {@link Image} where each pixel's:
     * <ul>
     * <li>{@code R} channel is the progress along the {@code X} axis.</li>
     * <li>{@code G} channel is 0.</li>
     * <li>{@code B} channel is 0.</li>
     * </ul>
     * @param pixelWriter The {@link PixelWriter} to write to
     */
    private void xCoordinateGenerator(PixelWriter pixelWriter) {
        for (var x = 0; x < imageSize.width(); x++) {
            for (var y = 0; y < imageSize.height(); y++) {
                var r = x / imageSize.widthAsDouble();

                pixelWriter.setColor(x, y, Color.color(r, 0, 0));
            }
        }
    }

    /**
     * Generates an {@link Image} where each pixel's:
     * <ul>
     * <li>{@code R} channel is 0.</li>
     * <li>{@code G} channel is the progress along the {@code Y} axis.</li>
     * <li>{@code B} channel is 0.</li>
     * </ul>
     * @param pixelWriter The {@link PixelWriter} to write to
     */
    private void yCoordinateGenerator(PixelWriter pixelWriter) {
        for (var x = 0; x < imageSize.width(); x++) {
            for (var y = 0; y < imageSize.height(); y++) {
                var g = y / imageSize.heightAsDouble();

                pixelWriter.setColor(x, y, Color.color(0, g, 0));
            }
        }
    }

    /**
     * Generates an {@link Image} where each pixel's:
     * <ul>
     * <li>{@code R} channel is the progress along the {@code X} axis.</li>
     * <li>{@code G} channel is the progress along the {@code Y} axis.</li>
     * <li>{@code B} channel is 0.</li>
     * </ul>
     * @param pixelWriter The {@link PixelWriter} to write to
     */
    private void xyCoordinatesGenerator(PixelWriter pixelWriter) {
        for (var x = 0; x < imageSize.width(); x++) {
            for (var y = 0; y < imageSize.height(); y++) {
                var r = x / imageSize.widthAsDouble();
                var g = y / imageSize.heightAsDouble();

                pixelWriter.setColor(x, y, Color.color(r, g, 0));
            }
        }
    }

    /**
     * Generates an {@link Image} where each pixel's:
     * <ul>
     * <li>{@code R} channel is the progress along the {@code X} axis.</li>
     * <li>{@code G} channel is the progress along the {@code Y} axis.</li>@link File}
     * <li>{@code B} channel is the average of the two.</li>
     * </ul>
     * @param pixelWriter The {@link PixelWriter} to write to
     */
    private void xyCoordinatesAverageGenerator(PixelWriter pixelWriter) {
        for (var x = 0; x < imageSize.width(); x++) {
            for (var y = 0; y < imageSize.height(); y++) {
                var r = x / imageSize.widthAsDouble();
                var g = y / imageSize.heightAsDouble();
                var b = (r + g) / 2;

                pixelWriter.setColor(x, y, Color.color(r, g, b));
            }
        }
    }

    /**
     * Generates an {@link Image} where each pixel's:
     * <ul>
     * <li>{@code R} channel is the {@code sin} of the {@code X} coordinate.</li>
     * <li>{@code G} channel is the {@code sin} of the {@code Y} coordinate.</li>
     * <li>{@code B} channel is the average of the two.</li>
     * </ul>
     * @param pixelWriter The {@link PixelWriter} to write to
     */
    private void sinGenerator(PixelWriter pixelWriter) {
        for (var x = 0; x < imageSize.width(); x++) {
            for (var y = 0; y < imageSize.height(); y++) {
                var r = (Math.sin(x) + 1) / 2;
                var g = (Math.sin(y) + 1) / 2;
                var b = (r + g) / 2;

                pixelWriter.setColor(x, y, Color.color(r, g, b));
            }
        }
    }

    /**
     * Generates an {@link Image} with a colored in circle that:
     * <ul>
     *     <li>Has a radius of half the image's width.</li>
     *     <li>Is centered in the image.</li>
     *     <li>Is on top of a differently colored background.</li>
     * </ul>
     * <p>
     * The generated colors are randomized on each run.
     * @param pixelWriter The {@link PixelWriter} to write to
     */
    private void circleGenerator(PixelWriter pixelWriter) {
        var centerX = imageSize.width() / 2;
        var centerY = imageSize.height() / 2;
        var radius = Math.pow(centerX, 2);

        var backgroundColor = Color.color(Math.random(), Math.random(), Math.random());
        var circleColor = Color.color(Math.random(), Math.random(), Math.random());

        for (var x = 0; x < imageSize.width(); x++) {
            for (var y = 0; y < imageSize.height(); y++) {
                var xResult = Math.pow(x - centerX, 2);
                var yResult = Math.pow(y - centerY, 2);
                var isInside = (xResult + yResult) <= radius;

                pixelWriter.setColor(x, y, isInside ? circleColor : backgroundColor);
            }
        }
    }

    /**
     * Generates an {@link Image} with a colored in circle that:
     * <ul>
     *     <li>Has a radius of half the image's width.</li>
     *     <li>Is centered in the image.</li>
     *     <li>Is on top of a differently colored background.</li>
     *     <li>Fades into the background color, fading completely on the edge.</li>
     * </ul>
     * <p>
     * The generated colors are randomized on each run.
     * @param pixelWriter The {@link PixelWriter} to write to
     */
    private void circleFalloffGenerator(PixelWriter pixelWriter) {
        var centerX = imageSize.width() / 2;
        var centerY = imageSize.height() / 2;
        var radiusSquared = Math.pow(centerX, 2);

        var backgroundColor = Color.color(Math.random(), Math.random(), Math.random());
        var circleColor = Color.color(Math.random(), Math.random(), Math.random());

        for (var x = 0; x < imageSize.width(); x++) {
            for (var y = 0; y < imageSize.height(); y++) {
                var xResult = Math.pow(x - centerX, 2);
                var yResult = Math.pow(y - centerY, 2);
                var isInside = (xResult + yResult) <= radiusSquared;

                if (!isInside) {
                    pixelWriter.setColor(x, y, backgroundColor);
                    continue;
                };

                var distanceFromCenter = Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2));
                var backgroundColorIntensity = Math.clamp(Math.pow(distanceFromCenter / centerX, 2), 0, 1);
                var circleColorIntensity = Math.clamp(Math.pow(1 - backgroundColorIntensity, 2), 0, 1);

                var mixedColor = Color.color(
                        backgroundColor.getRed() * backgroundColorIntensity + circleColor.getRed() * circleColorIntensity,
                        backgroundColor.getGreen() * backgroundColorIntensity + circleColor.getGreen() * circleColorIntensity,
                        backgroundColor.getBlue() * backgroundColorIntensity + circleColor.getBlue() * circleColorIntensity
                );

                pixelWriter.setColor(x, y, mixedColor);
            }
        }
    }

    /**
     * Generates an {@link Image} displaying the Mandelbrot set, the color of a pixel is either:
     * <ul>
     *     <li>Black if the pixel's coordinates are inside the Mandelbrot set.</li>
     *     <li>The iteration count transformed into a HSB color if not inside the Mandelbrot set.</li>
     * </ul>
     * <p>
     * The algorithm's iteration count can be changed with the {@link #MANDELBROT_ITERATIONS} constant.
     * @see <a href="https://en.wikipedia.org/wiki/Mandelbrot_set">https://en.wikipedia.org/wiki/Mandelbrot_set</a>
     * @param pixelWriter The {@link PixelWriter} to write to
     */
    private void mandelbrotGenerator(PixelWriter pixelWriter) {
        var originalXRange = (imageSize.width());
        var originalYRange = (imageSize.height());

        var newXRange = (0.74 - (-2));
        var newYRange = (1.12 - (-1.12));

        for (var x = 0; x < imageSize.width(); x++) {
            for (var y = 0; y < imageSize.height(); y++) {
                // (((OldValue - OldMin) * NewRange) / OldRange) + NewMin
                var mappedX = ((x * newXRange) / originalXRange) - 2;
                var mappedY = ((y * newYRange) / originalYRange) - 1.12;

                var generatedX = 0d;
                var generatedY = 0d;
                var iterations = 0;

                while (generatedX * generatedX + generatedY * generatedY <= 4 && iterations < MANDELBROT_ITERATIONS) {
                    var xTemp = generatedX * generatedX - generatedY * generatedY + mappedX;
                    generatedY = 2 * generatedX * generatedY + mappedY;
                    generatedX = xTemp;

                    iterations++;
                }

                if (iterations == MANDELBROT_ITERATIONS) {
                    pixelWriter.setColor(x, y, Color.BLACK);
                } else {
                    var usedIterations = Math.clamp((double) iterations / MANDELBROT_ITERATIONS + 0.25, 0, 1);
                    var hue = Math.pow(usedIterations * 360, 1.5) % 360;

                    pixelWriter.setColor(x, y, Color.hsb(hue, 1, usedIterations));
                }
            }
        }
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public ImageSize getImageSize() {
        return imageSize;
    }
}
