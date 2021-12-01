package com.ecxfoi.wbl.wienerbergerfrontend.di.modules;

import androidx.lifecycle.ViewModelProvider;

import com.ecxfoi.wbl.wienerbergerfrontend.di.providers.DeliveryNotesFragmentProvider;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.deliverynotes.DeliveryNotesViewModel;
import com.ecxfoi.wbl.wienerbergerfrontend.utils.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class DeliveryNotesModule
{
    @Provides
    DeliveryNotesViewModel deliveryNotesViewModel()
    {
        return new DeliveryNotesViewModel();
    }

    @Provides
    ViewModelProvider.Factory provideViewModelProvider(DeliveryNotesViewModel viewModel)
    {
        return new ViewModelProviderFactory<>(viewModel);
    }
}

