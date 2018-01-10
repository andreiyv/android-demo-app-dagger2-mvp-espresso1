package com.test.xyz.daggersample1.ui.activity;

import com.test.xyz.daggersample1.di.ActivityScope;
import com.test.xyz.daggersample1.di.AppComponent;

import dagger.Component;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(
        modules = {MainActivityModule.class}
)
public interface MainActivityComponent {
    void inject(MainActivity activity);
}

