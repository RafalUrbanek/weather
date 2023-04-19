package com.trainup.weather.domain.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ResourceBundle;

public class LinkBuilder {

    static ResourceBundle res = ResourceBundle.getBundle("secrets");

    public static URI uriGeoConstructor (String lat, String lon) throws URISyntaxException {
        StringBuffer sb = new StringBuffer();
        sb.append("http://api.openweathermap.org/geo/1.0/direct?q=");
        sb.append(lat);
        sb.append(",");
        sb.append(lon);
        sb.append("&limit=1&appid=");
        sb.append(res.getString("LinkBuilder.appId"));
        return new URI(replaceSpaces(sb));
    }

    public static URI uriWeatherConstructor (Double lat, Double lon) throws URISyntaxException {
        StringBuffer sb = new StringBuffer();
        sb.append("https://api.openweathermap.org/data/2.5/weather?lat=");
        sb.append(lat);
        sb.append("&lon=");
        sb.append(lon);
        sb.append("&appid=");
        sb.append(res.getString("LinkBuilder.appId"));
        return new URI(sb.toString());
    }

    private static String replaceSpaces(StringBuffer text) {
        StringBuilder fixedText = new StringBuilder();

        for(Character c : text.toString().toCharArray()) {
            if (c.equals(' ')) {
                fixedText.append("_");
            } else {
                fixedText.append(c);
            }
        }
        return fixedText.toString();
    }
}
