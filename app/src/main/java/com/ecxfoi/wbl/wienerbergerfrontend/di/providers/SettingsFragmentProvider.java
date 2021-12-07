package com.ecxfoi.wbl.wienerbergerfrontend.di.providers;

import com.ecxfoi.wbl.wienerbergerfrontend.di.modules.SettingsFragmentModule;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.settings.SettingsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SettingsFragmentProvider
{
    @ContributesAndroidInjector(modules = SettingsFragmentModule.class)
    abstract SettingsFragment provideSettingsFragmentFactory();
}