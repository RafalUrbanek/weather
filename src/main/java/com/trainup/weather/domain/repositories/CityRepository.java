package com.trainup.weather.domain.repositories;

import com.trainup.weather.domain.entities.City;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, Integer> {

    void deleteCityByNameAndCountry(String cityName, String countryCode);
}
