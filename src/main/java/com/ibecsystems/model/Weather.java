package com.ibecsystems.model;

import lombok.Data;


@Data
public class Weather {
    private Long cityCode;
    private double temp;
    private double feelsLike;
    private double tempMin;
    private double tempMax;

    public Weather(Long cityCode) {
        this.cityCode = cityCode;
    }
}
