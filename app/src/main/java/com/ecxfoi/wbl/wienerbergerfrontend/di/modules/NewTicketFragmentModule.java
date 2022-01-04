package com.ecxfoi.wbl.wienerbergerfrontend.di.modules;

import androidx.lifecycle.ViewModelProvider;

import com.ecxfoi.wbl.wienerbergerfrontend.repositories.TicketsRepository;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.supporttickets.NewTicketViewModel;
import com.ecxfoi.wbl.wienerbergerfrontend.utils.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class NewTicketFragmentModule
{
    @Provides
    NewTicketViewModel provideNewTicketViewModel(TicketsRepository ticketRepository)
    {
        return new NewTicketViewModel(ticketRepository);
    }

    @Provides
    ViewModelProvider.Factory provideViewModelProvider(NewTicketViewModel viewModel)
    {
        return new ViewModelProviderFactory<>(viewModel);
    }
}