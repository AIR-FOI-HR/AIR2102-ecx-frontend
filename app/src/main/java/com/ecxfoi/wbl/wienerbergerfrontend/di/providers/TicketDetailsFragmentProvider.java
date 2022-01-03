package com.ecxfoi.wbl.wienerbergerfrontend.di.providers;

import com.ecxfoi.wbl.wienerbergerfrontend.di.modules.TicketDetailsFragmentModule;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.supporttickets.TicketDetailsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class TicketDetailsFragmentProvider
{
    @ContributesAndroidInjector(modules = TicketDetailsFragmentModule.class)
    abstract TicketDetailsFragment provideTicketDetailsFragmentFactory();
}