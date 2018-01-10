package com.test.xyz.daggersample1.service.api;

import com.test.xyz.daggersample1.service.exception.InvalidCityException;

public interface WeatherService {
    public int getWeatherInfo(String city) throws InvalidCityException;
}
