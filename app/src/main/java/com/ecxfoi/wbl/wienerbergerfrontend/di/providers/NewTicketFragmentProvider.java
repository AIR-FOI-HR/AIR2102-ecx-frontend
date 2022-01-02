package com.ecxfoi.wbl.wienerbergerfrontend.di.providers;

import com.ecxfoi.wbl.wienerbergerfrontend.di.modules.NewTicketFragmentModule;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.supporttickets.NewTicketFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class NewTicketFragmentProvider
{
    @ContributesAndroidInjector(modules = NewTicketFragmentModule.class)
    abstract NewTicketFragment provideNewTicketFragmentFactory();
}