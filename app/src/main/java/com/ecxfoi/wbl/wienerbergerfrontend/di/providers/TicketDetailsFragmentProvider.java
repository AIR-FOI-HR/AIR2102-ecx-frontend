package com.ecxfoi.wbl.wienerbergerfrontend.di.providers;

import com.ecxfoi.wbl.wienerbergerfrontend.di.modules.NewTicketFragmentModule;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.supporttickets.TicketDetailsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class TicketDetailsFragmentProvider
{
    @ContributesAndroidInjector(modules = NewTicketFragmentModule.class)
    abstract TicketDetailsFragment provideTicketDetailsFragmentFactory();
}