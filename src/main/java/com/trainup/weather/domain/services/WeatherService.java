package com.trainup.weather.domain.services;

import com.trainup.weather.domain.Dto.WeatherDto;
import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;

public interface WeatherService {

    WeatherDto getWeather(Double longitude, Double latitude) throws URISyntaxException, IOException, InterruptedException, JSONException;

}
