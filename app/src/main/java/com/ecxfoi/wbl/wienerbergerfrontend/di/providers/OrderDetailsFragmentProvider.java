package com.ecxfoi.wbl.wienerbergerfrontend.di.providers;

import com.ecxfoi.wbl.wienerbergerfrontend.di.modules.OrderDetailsFragmentModule;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.orders.OrderDetailsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class OrderDetailsFragmentProvider
{
    @ContributesAndroidInjector(modules = OrderDetailsFragmentModule.class)
    abstract OrderDetailsFragment provideOrderDetailsFragmentFactory();
}