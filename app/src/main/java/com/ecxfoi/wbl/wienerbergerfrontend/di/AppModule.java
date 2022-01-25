package com.ecxfoi.wbl.wienerbergerfrontend.di;

import android.app.Application;
import android.content.Context;

import com.ecxfoi.wbl.wienerbergerfrontend.api.SelectedCompanyData;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule
{
    @Provides
    @Singleton
    Context provideContext(Application application)
    {
        return application;
    }

    @Provides
    @Singleton
    SelectedCompanyData provideSelectedCompanyData()
    {
        return new SelectedCompanyData();
    }
}

