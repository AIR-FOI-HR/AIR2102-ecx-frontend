package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.settings;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ecxfoi.wbl.wienerbergerfrontend.base.BaseFragment;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.orders.OrdersViewModel;

import javax.inject.Inject;

public class SettingsFragment extends BaseFragment<SettingsViewModel>
{
    @Inject
    private ViewModelProvider.Factory factory;

    private SettingsViewModel viewModel;

    @Override
    public SettingsViewModel getViewModel() {
        viewModel = new ViewModelProvider(this, factory).get(SettingsViewModel.class);
        return viewModel;
    }
}