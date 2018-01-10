package com.test.xyz.daggersample1;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.test.xyz.daggersample1.di.AppComponent;
import com.test.xyz.daggersample1.di.AppModule;
import com.test.xyz.daggersample1.di.DaggerApplication;
import com.test.xyz.daggersample1.service.api.HelloService;
import com.test.xyz.daggersample1.service.api.WeatherService;
import com.test.xyz.daggersample1.service.exception.InvalidCityException;
import com.test.xyz.daggersample1.ui.activity.MainActivity;
import com.test.xyz.daggersample1.util.DaggerActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    private static String GREET_PREFIX = "[Test] Hello ";
    private static int MOCK_TEMPERATURE = 65;
    private static String MOCK_NAME = "Hazem";
    private static String MOCK_PLACE = "Cairo, Egypt";
    private static String MOCK_GREETING_MSG = GREET_PREFIX + MOCK_NAME;
    private static String MOCK_WEATHER_MSG = "\nCurrent weather in " + MOCK_PLACE + " is " + MOCK_TEMPERATURE + "°F";

    private static String MOCK_RESPONSE_MESSAGE = MOCK_GREETING_MSG + MOCK_WEATHER_MSG;

    private static String TAG = MainActivityTest.class.getName();

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new DaggerActivityTestRule<>(MainActivity.class, new DaggerActivityTestRule.OnBeforeActivityLaunchedListener<MainActivity>() {
                @Override
                public void beforeActivityLaunched(@NonNull Application application, @NonNull MainActivity activity) {
                    DaggerApplication app = (DaggerApplication) application;

                    AppComponent mTestAppComponent = DaggerMainActivityTest_TestAppComponent.builder()
                            .appModule(new AppModule(app))
                            .build();

                    app.setTestComponent(mTestAppComponent);
                }
            });

    @Singleton
    @Component(modules = {TestServiceModule.class, AppModule.class})
    interface TestAppComponent extends AppComponent {
    }

    @Module
    static class TestServiceModule {

        @Provides
        @Singleton
        HelloService provideHelloService() {
            return new HelloService() {
                @Override
                public String greet(String userName) {
                    return GREET_PREFIX + userName;
                }
            };
        }

        @Provides
        @Singleton
        WeatherService provideWeatherService() {
            return new WeatherService() {
                @Override
                public int getWeatherInfo(String city) throws InvalidCityException {
                    return 65;
                }
            };
        }
    }

    @Test
    public void greetButtonClicked() {
        onView(withId(R.id.userNameText))
                .perform(typeText(MOCK_NAME), closeSoftKeyboard());

        onView(withId(R.id.cityText)).perform(clearText(), typeText(MOCK_PLACE), closeSoftKeyboard());

        onView(withId(R.id.btnShowInfo)).perform(click());

        onView(withId(R.id.resultView)).check(matches(withText(MOCK_RESPONSE_MESSAGE)));
    }

}
