package com.ecxfoi.wbl.wienerbergerfrontend.di.modules;

import androidx.lifecycle.ViewModelProvider;

import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.supporttickets.CreateSupportTicketsViewModel;
import com.ecxfoi.wbl.wienerbergerfrontend.utils.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class CreateSupportTicketsFragmentModule
{
    @Provides
    CreateSupportTicketsViewModel provideCreateSupportTicketsViewModel()
    {
        return new CreateSupportTicketsViewModel();
    }

    @Provides
    ViewModelProvider.Factory provideViewModelProvider(CreateSupportTicketsViewModel viewModel)
    {
        return new ViewModelProviderFactory<>(viewModel);
    }
}