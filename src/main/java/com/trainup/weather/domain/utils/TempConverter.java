package com.trainup.weather.domain.utils;

import org.decimal4j.util.DoubleRounder;

public class TempConverter {

    public static Double convertKToC(Double k) {
        return DoubleRounder.round(k - 273.15, 2);
    }
}
