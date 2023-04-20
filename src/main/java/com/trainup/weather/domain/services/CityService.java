package com.trainup.weather.domain.services;

import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;

public interface CityService {

    void addCity(String cityName, String cityCode) throws URISyntaxException, IOException, InterruptedException, JSONException;

    void deleteCity(String cityName, String countryCode);
}
