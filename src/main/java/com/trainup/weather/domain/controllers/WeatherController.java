package com.trainup.weather.domain.controllers;

import com.trainup.weather.domain.Dto.WeatherDto;
import com.trainup.weather.domain.schedulers.CityUpdater;
import com.trainup.weather.domain.services.CityService;
import com.trainup.weather.domain.services.WeatherService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
public class WeatherController {

    @Autowired
    CityService cityService;

    @Autowired
    WeatherService weatherService;

    @PostMapping("/city")
    public void addCity(String cityName, String countryCode) throws URISyntaxException, IOException, InterruptedException, JSONException {
        cityService.addCity(cityName, countryCode);
    }

    @PostMapping("/scheduled")
    public void updateCityCoordinates() {
        CityUpdater.start(cityService);
    }

    @GetMapping("/weather")
    public void updateWeather(Double latitude, Double longitude) throws URISyntaxException, IOException, InterruptedException, JSONException {
        weatherService.updateWeather(latitude, longitude);
    }
}
