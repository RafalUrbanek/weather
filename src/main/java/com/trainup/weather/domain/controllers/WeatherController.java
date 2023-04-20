package com.trainup.weather.domain.controllers;

import com.trainup.weather.domain.services.CityService;
import com.trainup.weather.domain.services.WeatherService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
public class WeatherController {

    @Autowired
    private CityService cityService;

    @Autowired
    private WeatherService weatherService;

    @PostMapping("/city")
    public void addCity(String cityName, String countryCode) throws URISyntaxException, IOException, InterruptedException, JSONException {
        cityService.addCity(cityName, countryCode);
    }

    @DeleteMapping("/city")
    public void deleteCity(String cityName, String countryCode) {
        cityService.deleteCity(cityName, countryCode);
    }

    @GetMapping("/city")
    public String getBestWeatherCity() {
        return weatherService.bestWeatherCity();
    }
}
