package com.trainup.weather.domain.services;

import com.trainup.weather.domain.entities.City;
import com.trainup.weather.domain.utils.Coordinates;
import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;

public interface WeatherService {

    void updateWeather(Integer cityId) throws InterruptedException, IOException, JSONException, URISyntaxException;

    void updateWeathers() throws InterruptedException, IOException, JSONException, URISyntaxException;

    City bestWeather(Coordinates coordinates);

    City bestWeather(String cityName, String countryName);
}
