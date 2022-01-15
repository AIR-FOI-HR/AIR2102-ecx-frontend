package com.ecxfoi.wbl.wienerbergerfrontend.di.modules;

import androidx.lifecycle.ViewModelProvider;

import com.ecxfoi.wbl.wienerbergerfrontend.repositories.OrdersRepository;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.orders.OrderDetailsViewModel;
import com.ecxfoi.wbl.wienerbergerfrontend.utils.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class OrderDetailsFragmentModule
{
    @Provides
    OrderDetailsViewModel provideOrderDetailsViewModel(OrdersRepository ordersRepository)
    {
        return new OrderDetailsViewModel(ordersRepository);
    }

    @Provides
    ViewModelProvider.Factory provideViewModelProvider(OrderDetailsViewModel viewModel)
    {
        return new ViewModelProviderFactory<>(viewModel);
    }
}
