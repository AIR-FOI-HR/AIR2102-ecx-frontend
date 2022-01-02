package com.ecxfoi.wbl.wienerbergerfrontend.di.modules;

import androidx.lifecycle.ViewModelProvider;

import com.ecxfoi.wbl.wienerbergerfrontend.repositories.TicketsRepository;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.supporttickets.SupportTicketsViewModel;
import com.ecxfoi.wbl.wienerbergerfrontend.utils.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class SupportTicketsFragmentModule
{
    @Provides
    SupportTicketsViewModel provideSupportTicketsViewModel(TicketsRepository ticketsRepository)
    {
        return new SupportTicketsViewModel(ticketsRepository);
    }

    @Provides
    ViewModelProvider.Factory provideViewModelProvider(SupportTicketsViewModel viewModel)
    {
        return new ViewModelProviderFactory<>(viewModel);
    }
}
