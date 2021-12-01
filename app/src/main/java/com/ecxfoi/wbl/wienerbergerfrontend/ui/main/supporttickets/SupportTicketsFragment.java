package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.supporttickets;

import androidx.lifecycle.ViewModelProvider;

import com.ecxfoi.wbl.wienerbergerfrontend.base.BaseFragment;

import javax.inject.Inject;

public class SupportTicketsFragment extends BaseFragment<SupportTicketsViewModel>
{
    @Inject
    private ViewModelProvider.Factory factory;

    private SupportTicketsViewModel viewModel;

    @Override
    public SupportTicketsViewModel getViewModel()
    {
        viewModel = new ViewModelProvider(this, factory).get(SupportTicketsViewModel.class);
        return viewModel;
    }
}