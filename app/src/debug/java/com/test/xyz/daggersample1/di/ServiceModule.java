package com.test.xyz.daggersample1.di;

import com.test.xyz.daggersample1.service.api.HelloService;
import com.test.xyz.daggersample1.service.api.WeatherService;
import com.test.xyz.daggersample1.service.impl.HelloServiceDebugManager;
import com.test.xyz.daggersample1.service.impl.WeatherServiceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ServiceModule {

    @Provides
    @Singleton
    HelloService provideHelloService() {
        return new HelloServiceDebugManager();
    }

    @Provides
    @Singleton
    WeatherService provideWeatherService() {
        return new WeatherServiceManager();
    }
}
