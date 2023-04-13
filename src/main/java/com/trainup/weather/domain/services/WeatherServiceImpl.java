package com.trainup.weather.domain.services;

import com.trainup.weather.domain.Dto.WeatherDto;
import com.trainup.weather.domain.entities.WeatherReport;
import com.trainup.weather.domain.repositories.WeatherRepository;
import com.trainup.weather.domain.utils.LinkBuilder;
import com.trainup.weather.domain.utils.TempConverter;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDate;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    WeatherRepository weatherRepository;

    @Override
    public void updateWeather(Double latitude, Double longitude) throws InterruptedException, IOException, JSONException, URISyntaxException {
        WeatherReport weatherReport = new WeatherReport();
        WeatherDto weather = getWeather(latitude, longitude);
        weatherReport.setDate(LocalDate.now());
        weatherReport.setTemperature(weather.getTemperature());
        weatherReport.setWindSpeed(weather.getWindSpeed());
        weatherRepository.save(weatherReport);
    }

    private WeatherDto getWeather(Double latitude, Double longitude) throws URISyntaxException, IOException, InterruptedException, JSONException {

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
