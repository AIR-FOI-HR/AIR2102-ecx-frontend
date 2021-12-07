package com.ecxfoi.wbl.wienerbergerfrontend.di.providers;

import com.ecxfoi.wbl.wienerbergerfrontend.di.modules.SupportTicketsFragmentModule;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.supporttickets.SupportTicketsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SupportTicketsFragmentProvider
{
    @ContributesAndroidInjector(modules = SupportTicketsFragmentModule.class)
    abstract SupportTicketsFragment provideSupportTicketsFragmentFactory();
}