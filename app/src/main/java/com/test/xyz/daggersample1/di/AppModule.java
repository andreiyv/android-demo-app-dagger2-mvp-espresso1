package com.test.xyz.daggersample1.di;

import android.app.Application;
import android.content.res.Resources;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    DaggerApplication app;

    public AppModule(DaggerApplication application) {
        app = application;
    }

    @Provides
    @Singleton
    protected Application provideApplication() {
        return app;
    }


    @Provides
    @Singleton
    protected Resources provideResources() {
        return app.getResources();
    }
}
