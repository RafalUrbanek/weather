package com.trainup.weather.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "WEATHER")
public class WeatherReport implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Double Id;

    @Column
    Double temperature;

    @Column
    Double windSpeed;

    @Column
    LocalDate date;
}
