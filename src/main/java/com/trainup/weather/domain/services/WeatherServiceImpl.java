package com.trainup.weather.domain.services;

import com.trainup.weather.domain.Dto.WeatherDto;
import com.trainup.weather.domain.entities.City;
import com.trainup.weather.domain.entities.Weather;
import com.trainup.weather.domain.repositories.CityRepository;
import com.trainup.weather.domain.repositories.WeatherRepository;
import com.trainup.weather.domain.utils.LinkBuilder;
import com.trainup.weather.domain.utils.TempConverter;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private CityRepository cityRepository;

    @Scheduled(fixedDelay = 30000)
    public void updateWeathers() {
        cityRepository.findAll().forEach(city -> {
            try {
                updateWeather(city.getId());
            } catch (InterruptedException | IOException | JSONException | URISyntaxException e) {
                e.printStackTrace();
            }
        });
    }

    private void updateWeather(Integer cityId) throws InterruptedException, IOException, JSONException, URISyntaxException {
        if (cityRepository.findById(cityId).isPresent()) {
            City city = cityRepository.findById(cityId).get();
            Weather weather = new Weather();
            if (weatherRepository.findWeatherByDateAndCity(LocalDate.now(), city) == null) {
                WeatherDto weatherDto = getWeather(city);
                weather.setDate(LocalDate.now());
                weather.setTemperature(weatherDto.getTemperature());
                weather.setWindSpeed(weatherDto.getWindSpeed());
                weather.setCity(city);
                weatherRepository.save(weather);
                System.out.println("updated weather for city: " + city.getName());
            }
        }
    }

    private WeatherDto getWeather(City city) throws URISyntaxException, IOException, InterruptedException, JSONException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(LinkBuilder.uriWeatherConstructor(city.getLatitude(), city.getLongitude()))
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
                .windSpeed(response.getJSONObject("wind").getDouble("speed"))
                .build();
    }

    @Override
    public String bestWeatherCity() {
        City bestCity = null;
        double score = 0;
        List<Weather> weathers = new ArrayList<>();

        cityRepository.findAll().forEach(city -> {
            if (weatherRepository.findById(city.getId()).isPresent()) {
                weathers.add(weatherRepository.findByCityAndDate(city, LocalDate.now()));
            }
        });
            for (Weather weather : weathers) {
                double currentScore = weather.getWindSpeed() * 3 + weather.getTemperature();
                if (bestCity == null) {
                    bestCity = weather.getCity();
                    score = currentScore;
                } else {
                    if (score < currentScore) {
                        bestCity = weather.getCity();
                        score = currentScore;
                    }
                }
            }
            if (bestCity != null) {
                return bestCity.getName();
            } else {
                return null;
            }
    }
}
