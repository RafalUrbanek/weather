package com.trainup.weather.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "WEATHER")
public class Weather implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer WeatherId;

    @ManyToOne
    City city;

    @Column
    Double temperature;

    @Column
    Double windSpeed;

    @Column
    LocalDate date;
}
