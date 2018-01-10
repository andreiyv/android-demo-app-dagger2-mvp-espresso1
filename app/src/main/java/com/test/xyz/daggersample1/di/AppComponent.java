package com.test.xyz.daggersample1.di;

import android.app.Application;

import com.test.xyz.daggersample1.ui.activity.MainActivityComponent;
import com.test.xyz.daggersample1.ui.activity.MainActivityModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ServiceModule.class})
public interface AppComponent {

    MainActivityComponent plus(MainActivityModule module);
    Application application();
}
