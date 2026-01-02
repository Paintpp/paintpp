package com.purkynka.paintpp.logic.util;

import java.awt.*;
import java.util.Random;

public class ColorUtil {
    public static Color getAveragedColor(Color color) {
        var average = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
        return new Color(average, average, average);
    }

    public static Color getRandomColor(Random random) {
        return new Color(random.nextInt(0, 256), random.nextInt(0, 256), random.nextInt(0, 256));
    }

    public static Color getInterpolatedColor(Color first, Color second, double secondColorAmount) {
        var newRed = first.getRed() + (second.getRed() - first.getRed()) * secondColorAmount;
        var newGreen = first.getGreen() + (second.getGreen() - first.getGreen()) * secondColorAmount;
        var newBlue = first.getBlue() + (second.getBlue() - first.getBlue()) * secondColorAmount;

        return new Color((int) newRed, (int) newGreen, (int) newBlue);
    }
}
