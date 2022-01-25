package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.customers;

import android.content.Intent;

import androidx.lifecycle.ViewModelProvider;

import com.ecxfoi.wbl.wienerbergerfrontend.base.BaseFragment;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.companyselection.CompanySelectionActivity;

import javax.inject.Inject;

public class CustomersFragment extends BaseFragment<CustomersViewModel>
{
    @Inject
    ViewModelProvider.Factory factory;

    @Override
    public CustomersViewModel getViewModel()
    {
        final CustomersViewModel viewModel = new ViewModelProvider(this, factory).get(CustomersViewModel.class);
        Intent intent = new Intent(getContext(), CompanySelectionActivity.class);
        startActivity(intent);
        return viewModel;
    }
}
