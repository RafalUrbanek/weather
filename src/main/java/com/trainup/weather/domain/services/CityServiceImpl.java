package com.trainup.weather.domain.services;

import com.trainup.weather.domain.entities.City;
import com.trainup.weather.domain.repositories.CityRepository;
import com.trainup.weather.domain.utils.Coordinates;
import com.trainup.weather.domain.utils.LinkBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepository cityRepository;

    @Override
    public boolean addCity(String cityName, String countryCode) throws InterruptedException, URISyntaxException, JSONException, IOException {
        Coordinates coordinates = getCoordinates(cityName, countryCode);
        City city = new City();
        city.setName(cityName);
        city.setCountry(countryCode);
        city.setLatitude(coordinates.getLatitude());
        city.setLongitude(coordinates.getLongitude());
        cityRepository.save(city);
        return true;
    }

    private boolean updateCity(Integer id) throws InterruptedException, IOException, JSONException, URISyntaxException {
        boolean isUpdated = false;
        Optional<City> city = cityRepository.findById(id);
        if (city.isPresent() && (city.get().getLongitude() == null || city.get().getLatitude() == null)) {
            updateCoordinates(city.get());
            cityRepository.save(city.get());
            isUpdated = true;
        }
        return isUpdated;
    }

    public int updateCities() {
        AtomicInteger cityCounter = new AtomicInteger();
        cityRepository.findAll().forEach(city -> {
            try {
                if (updateCity(city.getId())) {
                    cityCounter.addAndGet(1);
                }
            } catch (InterruptedException | IOException | JSONException | URISyntaxException e) {
                e.printStackTrace();
            }
        });
        return cityCounter.get();
    }

    private void updateCoordinates(City city) throws InterruptedException, IOException, JSONException, URISyntaxException {
            Coordinates coordinates = getCoordinates(city.getName(), city.getCountry());
            city.setLatitude(coordinates.getLatitude());
            city.setLongitude(coordinates.getLongitude());
    }

    private Coordinates getCoordinates(String cityName, String countryCode) throws URISyntaxException, IOException, InterruptedException, JSONException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(LinkBuilder.uriGeoConstructor(cityName, countryCode))
                .timeout(Duration.ofSeconds(5))
                .GET()
                .build();

        HttpClient client = HttpClient.newHttpClient();
        JSONArray responseJson = new JSONArray(client.send(request, HttpResponse.BodyHandlers.ofString()).body());
        if (!responseJson.isNull(0)) {
            return new Coordinates(
                    (Double) responseJson.getJSONObject(0).get("lat"),
                    (Double) responseJson.getJSONObject(0).get("lon")
            );
        }
        return new Coordinates(null, null);
    }
}
