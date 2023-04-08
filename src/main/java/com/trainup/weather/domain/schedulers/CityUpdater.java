package com.trainup.weather.domain.schedulers;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class CityUpdater {
    public static void start() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // TODO
            }
        }, 0, TimeUnit.SECONDS.toMillis(10));
    }
}
