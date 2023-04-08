package com.trainup.weather.domain.controllers;

import com.trainup.weather.domain.Dto.WeatherDto;
import com.trainup.weather.domain.services.CityService;
import com.trainup.weather.domain.services.CityServiceImpl;
import com.trainup.weather.domain.utils.Coordinates;
import com.trainup.weather.domain.utils.LinkBuilder;
import com.trainup.weather.domain.utils.TempConverter;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@RestController
public class WeatherController {

    @Autowired
    CityService cityService;

    @PostMapping("/city")
    public void addCity(String cityName,  String countryCode) throws URISyntaxException, IOException, InterruptedException, JSONException {
        cityService.addCity(cityName, countryCode);
    }

    @PatchMapping("/city")
    public void updateCity(String cityName, String countryCode) {
        cityService.updateCity(cityName, countryCode);
    }

    @GetMapping("/weather")
    public WeatherDto getWeather(Double latitude, Double longitude) throws URISyntaxException, IOException, InterruptedException, JSONException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(LinkBuilder.uriWeatherConstructor(latitude, longitude))
                .timeout(Duration.ofSeconds(5))
                .GET()
                .build();

        HttpClient client = HttpClient.newHttpClient();
        JSONObject responseJson = new JSONObject(client.send(request, HttpResponse.BodyHandlers.ofString()).body());
        return getWeatherDto(responseJson);
    }

    private static WeatherDto getWeatherDto(JSONObject response) throws JSONException {
        return WeatherDto.builder()
                .temperature(TempConverter.convertKToC(response.getJSONObject("main").getDouble("temp")))
                .humidity(response.getJSONObject("main").getInt("humidity"))
                .pressure(response.getJSONObject("main").getInt("pressure"))
                .windSpeed(response.getJSONObject("wind").getDouble("speed"))
                .build();
    }
}
