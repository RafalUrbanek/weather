package com.trainup.weather.domain.repositories;

import com.trainup.weather.domain.entities.City;
import com.trainup.weather.domain.entities.Weather;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface WeatherRepository extends CrudRepository<Weather, Integer> {

    Weather findWeatherByDateAndCity(LocalDate date, City city);

    Weather findByCityAndDate(City city, LocalDate date);
}
