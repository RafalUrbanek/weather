package com.trainup.weather.domain.services;

import com.trainup.weather.domain.utils.Coordinates;
import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;

public interface CityService {

    boolean addCity(String CityName, String CityCode) throws URISyntaxException, IOException, InterruptedException, JSONException;

    void updateCity(String cityName, String countryCode);
}
