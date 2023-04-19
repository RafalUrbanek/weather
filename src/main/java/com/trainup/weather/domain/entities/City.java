package com.trainup.weather.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "CITY")
public class City implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer Id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    @Column
    private Double latitude;

    @Column
    private Double longitude;
}
