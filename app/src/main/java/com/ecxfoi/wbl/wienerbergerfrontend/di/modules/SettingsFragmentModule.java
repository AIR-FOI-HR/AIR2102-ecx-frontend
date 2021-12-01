package com.ecxfoi.wbl.wienerbergerfrontend.di.modules;

import androidx.lifecycle.ViewModelProvider;

import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.settings.SettingsViewModel;
import com.ecxfoi.wbl.wienerbergerfrontend.utils.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class SettingsFragmentModule
{
    @Provides
    SettingsViewModel settingsViewModel()
    {
        return new SettingsViewModel();
    }

    @Provides
    ViewModelProvider.Factory provideViewModelProvider(SettingsViewModel viewModel)
    {
        return new ViewModelProviderFactory<>(viewModel);
    }
}
