package com.trainup.weather.domain.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CityDto {

    String name;

    String code;

    Double latitude;

    Double longitude;
}
