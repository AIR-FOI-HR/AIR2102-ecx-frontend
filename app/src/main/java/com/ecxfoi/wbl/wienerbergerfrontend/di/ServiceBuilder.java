package com.ecxfoi.wbl.wienerbergerfrontend.di;

import com.ecxfoi.wbl.wienerbergerfrontend.auth.AuthService;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ServiceBuilder
{
    @ContributesAndroidInjector
    abstract AuthService provideAuthService();
}
