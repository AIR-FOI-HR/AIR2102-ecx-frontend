package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.home;

import androidx.lifecycle.ViewModelProvider;

import com.ecxfoi.wbl.wienerbergerfrontend.base.BaseFragment;

import javax.inject.Inject;

public class HomeFragment extends BaseFragment<HomeViewModel>
{
    @Inject
    ViewModelProvider.Factory factory;

    private HomeViewModel viewModel;

    @Override
    public HomeViewModel getViewModel() {
        viewModel = new ViewModelProvider(this, factory).get(HomeViewModel.class);
        return viewModel;
    }
}