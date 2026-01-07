package com.purkynka.paintpp.logic.filter;

import java.text.DecimalFormat;
import java.time.Duration;

public class FilterApplyTime {
    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.##");
    public Duration rawTime;
    public Duration actualTime;

    public FilterApplyTime(Duration rawTime, Duration actualTime) {
        this.rawTime = rawTime;
        this.actualTime = actualTime;
    }

    public String getRawTimeSeconds() {
        return DECIMAL_FORMAT.format(rawTime.toMillis() / 1_000d);
    }

    public String getActualTimeSeconds() {
        return DECIMAL_FORMAT.format(actualTime.toMillis() / 1_000d);
    }
}
