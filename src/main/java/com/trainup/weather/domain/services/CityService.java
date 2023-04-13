package com.trainup.weather.domain.services;

import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;

public interface CityService {

    boolean addCity(String CityName, String CityCode) throws URISyntaxException, IOException, InterruptedException, JSONException;

    int updateCities();
}
