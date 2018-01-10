package com.test.xyz.daggersample1.interactor;

import android.text.TextUtils;
import android.util.Log;

import com.test.xyz.daggersample1.R;
import com.test.xyz.daggersample1.service.api.HelloService;
import com.test.xyz.daggersample1.service.api.WeatherService;
import com.test.xyz.daggersample1.service.exception.InvalidCityException;
import com.test.xyz.daggersample1.ui.presenter.OnInfoCompletedListener;

import javax.inject.Inject;

public class MainInteractorImpl implements MainInteractor {
    private static final String TAG = MainInteractorImpl.class.getName();

    @Inject
    HelloService helloService;

    @Inject
    WeatherService weatherService;

    @Inject
    public MainInteractorImpl() {
    }

    @Override
    public void getInformation(final String userName, final String cityName, final OnInfoCompletedListener listener) {
        final String greeting = helloService.greet(userName) + "\n";

        if (TextUtils.isEmpty(userName)) {
            listener.onUserNameValidationError(R.string.username_invalid_message);
            return;
        }

        if (TextUtils.isEmpty(cityName)) {
            listener.onUserNameValidationError(R.string.city_invalid_message);
            return;
        }

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    int temperature = weatherService.getWeatherInfo(cityName);
                    String temp = "Current weather in " + cityName + " is " + temperature + "°F";

                    listener.onSuccess(greeting + temp);
                } catch (InvalidCityException ex) {
                    listener.onFailure(ex.getMessage());
                    Log.e(TAG, ex.getMessage(), ex);
                } catch (Exception ex) {
                    listener.onFailure("Unable to get weather information");
                    Log.e(TAG, ex.getMessage(), ex);
                }
            }
        });

        thread.start();
    }
}
