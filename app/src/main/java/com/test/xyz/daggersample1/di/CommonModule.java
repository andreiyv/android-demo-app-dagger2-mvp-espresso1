package com.test.xyz.daggersample1.di;

import com.test.xyz.daggersample1.service.api.WeatherService;
import com.test.xyz.daggersample1.service.impl.WeatherServiceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CommonModule {

    @Provides
    @Singleton
    WeatherService provideWeatherService() {
        return new WeatherServiceManager();
    }
}
