package com.trainup.weather.domain.utils;

import java.net.URI;
import java.net.URISyntaxException;

public class LinkBuilder {

    public static URI uriGeoConstructor (Object... params) throws URISyntaxException {
        StringBuffer sb = new StringBuffer();
            sb.append("http://api.openweathermap.org/geo/1.0/direct?q=");

        for(int i = 0; i < params.length; i++) {
            sb.append(params[i]);
            if (i != params.length - 1) {
                sb.append(",");
            }
        }
        sb.append("&limit=1&appid=3ced6b0d7c2c0bab53979a5a84f729db");
        return new URI(sb.toString());
    }

    public static URI uriWeatherConstructor (Double lat, Double lon) throws URISyntaxException {
        StringBuffer sb = new StringBuffer();
        sb.append("https://api.openweathermap.org/data/2.5/weather?lat=");
        sb.append(lat);
        sb.append("&lon=");
        sb.append(lon);
        sb.append("&appid=3ced6b0d7c2c0bab53979a5a84f729db");
        return new URI(sb.toString());
    }
}
