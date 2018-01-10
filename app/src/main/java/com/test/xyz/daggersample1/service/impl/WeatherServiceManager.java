package com.test.xyz.daggersample1.service.impl;

import android.util.Log;

import com.test.xyz.daggersample1.service.api.ErrorMessages;
import com.test.xyz.daggersample1.service.api.WeatherService;
import com.test.xyz.daggersample1.service.exception.InvalidCityException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class WeatherServiceManager implements WeatherService {
    private static final String TAG = WeatherServiceManager.class.getName();

    @Override
    public int getWeatherInfo(String city) throws InvalidCityException {
        int temperature = 0;

        if (city == null) {
            throw new RuntimeException(ErrorMessages.CITY_REQUIRED);
        }

        try {
            city = URLEncoder.encode(city, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(ErrorMessages.INVALID_CITY_PROVIDED);
        }

        try {
            URL url = new URL("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22" + city + "%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setInstanceFollowRedirects(true);

            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0");

            // Receive response ...
            int responseCode = httpURLConnection.getResponseCode();

            InputStream is = httpURLConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = "";
            StringBuffer sb = new StringBuffer();

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            bufferedReader.close();

            String result = sb.toString();

            int startIndex = result.indexOf("\"temp\":");

            if (startIndex == -1) {
                throw new InvalidCityException(ErrorMessages.INVALID_CITY_PROVIDED);
            }

            int endIndex = result.indexOf(",", startIndex);

            temperature = Integer.parseInt(result.substring(startIndex + 8, endIndex - 1));
        } catch (InvalidCityException ex) {
            throw ex;
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage(), ex);
        }

        return temperature;
    }
}
