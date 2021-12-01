package com.ecxfoi.wbl.wienerbergerfrontend.di.modules;

import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.MainActivityViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule
{
    @Provides
    MainActivityViewModel provideMainActivityViewModel()
    {
        return new MainActivityViewModel();
    }
}
