package com.purkynka.paintpp.logic.util;

import java.awt.*;

public class ColorUtil {
    public static Color getAveragedColor(Color color) {
        var average = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
        return new Color(average, average, average);
    }
}
