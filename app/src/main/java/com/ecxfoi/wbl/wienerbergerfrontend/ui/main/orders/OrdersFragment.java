package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.orders;

import androidx.lifecycle.ViewModelProvider;

import com.ecxfoi.wbl.wienerbergerfrontend.base.BaseFragment;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.myaccount.MyAccountViewModel;

import javax.inject.Inject;

public class OrdersFragment extends BaseFragment<OrdersViewModel>
{
    @Inject
    private ViewModelProvider.Factory factory;

    private OrdersViewModel viewModel;

    @Override
    public OrdersViewModel getViewModel() {
        viewModel = new ViewModelProvider(this, factory).get(OrdersViewModel.class);
        return viewModel;
    }
}