package com.ecxfoi.wbl.wienerbergerfrontend.di.providers;

import com.ecxfoi.wbl.wienerbergerfrontend.di.modules.HomeFragmentModule;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.home.HomeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class HomeFragmentProvider
{
    @ContributesAndroidInjector(modules = HomeFragmentModule.class)
    abstract HomeFragment provideHomeFragmentFactory();
}
