package com.ibecsystems.service.impl;

import com.ibecsystems.model.Weather;
import com.ibecsystems.service.WeatherService;
import com.ibecsystems.utils.UrlParser;
import org.json.JSONObject;

public class WeatherServiceImpl implements WeatherService {

    final String URL_PART_ONE = "https://api.openweathermap.org/data/2.5/weather?id=";
    final String URL_PART_TWO = "&appid=8aa87be6d9d1095801504a4349c7ce01&units=metric";

    @Override
    public Weather createWeather(String cityCode) throws NumberFormatException {

        String output = UrlParser.getUrlContent(URL_PART_ONE + cityCode + URL_PART_TWO);
        Weather weather = new Weather(Long.parseLong(cityCode));
        if (!output.isEmpty()) {
            JSONObject object = new JSONObject(output).getJSONObject("main");
            weather.setTemp(object.getDouble("temp"));
            weather.setFeelsLike(object.getDouble("feels_like"));
            weather.setTempMin(object.getDouble("temp_min"));
            weather.setTempMax(object.getDouble("temp_max"));
        }

        return weather;

    }
}
