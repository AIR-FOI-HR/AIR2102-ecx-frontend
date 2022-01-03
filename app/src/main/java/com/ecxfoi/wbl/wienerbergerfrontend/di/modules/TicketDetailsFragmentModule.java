package com.ecxfoi.wbl.wienerbergerfrontend.di.modules;

import androidx.lifecycle.ViewModelProvider;

import com.ecxfoi.wbl.wienerbergerfrontend.repositories.TicketsRepository;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.supporttickets.TicketDetailsViewModel;
import com.ecxfoi.wbl.wienerbergerfrontend.utils.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class TicketDetailsFragmentModule
{
    @Provides
    TicketDetailsViewModel provideSupportTicketsViewModel(TicketsRepository ticketsRepository)
    {
        return new TicketDetailsViewModel(ticketsRepository);
    }

    @Provides
    ViewModelProvider.Factory provideViewModelProvider(TicketDetailsViewModel viewModel)
    {
        return new ViewModelProviderFactory<>(viewModel);
    }
}