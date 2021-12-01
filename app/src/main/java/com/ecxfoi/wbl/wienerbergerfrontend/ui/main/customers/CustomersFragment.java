package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.customers;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ecxfoi.wbl.wienerbergerfrontend.base.BaseFragment;

import javax.inject.Inject;

public class CustomersFragment extends BaseFragment<CustomersViewModel>
{
    @Inject
    ViewModelProvider.Factory factory;

    private CustomersViewModel viewModel;

    @Override
    public CustomersViewModel getViewModel() {
        viewModel = new ViewModelProvider(this, factory).get(CustomersViewModel.class);
        return viewModel;
    }
}
