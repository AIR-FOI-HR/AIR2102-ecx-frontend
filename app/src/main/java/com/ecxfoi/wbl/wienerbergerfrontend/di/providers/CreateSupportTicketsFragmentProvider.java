package com.ecxfoi.wbl.wienerbergerfrontend.di.providers;

import com.ecxfoi.wbl.wienerbergerfrontend.di.modules.CreateSupportTicketsFragmentModule;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.supporttickets.CreateSupportTicketsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CreateSupportTicketsFragmentProvider
{
    @ContributesAndroidInjector(modules = CreateSupportTicketsFragmentModule.class)
    abstract CreateSupportTicketsFragment provideCreateSupportTicketsFragmentFactory();
}