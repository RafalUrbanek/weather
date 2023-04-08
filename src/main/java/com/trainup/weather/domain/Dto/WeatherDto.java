package com.trainup.weather.domain.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeatherDto {

    Double temperature;

    Integer pressure;

    Integer humidity;

    Double windSpeed;


}
