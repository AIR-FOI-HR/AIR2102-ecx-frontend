package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.myaccount;

import androidx.lifecycle.ViewModelProvider;

import com.ecxfoi.wbl.wienerbergerfrontend.base.BaseFragment;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.home.HomeViewModel;

import javax.inject.Inject;

public class MyAccountFragment extends BaseFragment<MyAccountViewModel>
{
    @Inject
    private ViewModelProvider.Factory factory;

    private MyAccountViewModel viewModel;

    @Override
    public MyAccountViewModel getViewModel() {
        viewModel = new ViewModelProvider(this, factory).get(MyAccountViewModel.class);
        return viewModel;
    }
}