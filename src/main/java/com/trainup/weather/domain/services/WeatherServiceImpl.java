package com.trainup.weather.domain.services;

import com.trainup.weather.domain.Dto.WeatherDto;
import com.trainup.weather.domain.utils.LinkBuilder;
import com.trainup.weather.domain.utils.TempConverter;
import org.decimal4j.util.DoubleRounder;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class WeatherServiceImpl implements WeatherService {

    @Override
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
