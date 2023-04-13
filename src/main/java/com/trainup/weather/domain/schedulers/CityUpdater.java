package com.trainup.weather.domain.schedulers;

import com.trainup.weather.domain.services.CityService;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class CityUpdater {

    public static void start(CityService cityService) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("updated cities: " + cityService.updateCities());
            }
        }, 1000, TimeUnit.SECONDS.toMillis(10));
    }
}
