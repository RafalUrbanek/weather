package com.trainup.weather.domain.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Coordinates {

    private Double latitude;
    private Double longitude;

    @Override
    public String toString() {
        return "latitude: " + latitude + " longitude: " + longitude;
    }
}
