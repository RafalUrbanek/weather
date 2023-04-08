package com.trainup.weather;

import com.trainup.weather.domain.schedulers.CityUpdater;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
public class WeatherApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherApplication.class, args);

        CityUpdater.start();
//        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
//        taskScheduler.schedule(new CityUpdater(), new Date(System.currentTimeMillis() + 3000));
    }
}
