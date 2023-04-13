package com.trainup.weather.domain.repositories;

import com.trainup.weather.domain.entities.WeatherReport;
import org.springframework.data.repository.CrudRepository;

public interface WeatherRepository extends CrudRepository<WeatherReport, Integer> {
}
