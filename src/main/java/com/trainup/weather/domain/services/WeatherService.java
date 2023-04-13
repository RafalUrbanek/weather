package com.trainup.weather.domain.services;

import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;

public interface WeatherService {

    void updateWeather(Double longitude, Double latitude) throws InterruptedException, IOException, JSONException, URISyntaxException;
}
