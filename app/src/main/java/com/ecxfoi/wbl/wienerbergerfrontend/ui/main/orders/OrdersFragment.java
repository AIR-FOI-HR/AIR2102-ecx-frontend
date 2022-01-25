package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.orders;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ecxfoi.wbl.wienerbergerfrontend.R;
import com.ecxfoi.wbl.wienerbergerfrontend.base.BaseFragment;
import com.ecxfoi.wbl.wienerbergerfrontend.databinding.FragmentOrdersBinding;
import com.ecxfoi.wbl.wienerbergerfrontend.models.OrderData;

import java.util.ArrayList;

import javax.inject.Inject;

public class OrdersFragment extends BaseFragment<OrdersViewModel>
{
    @Inject
    ViewModelProvider.Factory factory;

    private FragmentOrdersBinding binding;
    private OrdersViewModel viewModel;

    @Override
    public OrdersViewModel getViewModel()
    {
        viewModel = new ViewModelProvider(this, factory).get(OrdersViewModel.class);
        return viewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState)
    {
        binding = FragmentOrdersBinding.inflate(inflater, container, false);

        viewModel.fetchOrderData().observe(getViewLifecycleOwner(), this::setOrdersData);

        return binding.getRoot();
    }

    private void setOrdersData(final ArrayList<OrderData> orderData)
    {
        binding.listView.setLayoutManager(new LinearLayoutManager(getActivity()));
        OrdersRecyclerAdapter recyclerAdapter = new OrdersRecyclerAdapter(orderData)
        {
            @Override
            public void onOrderSelected(final Long orderId)
            {
                final Activity allOrdersActivity = getActivity();
                if (allOrdersActivity != null)
                {
                    Bundle argument = new Bundle();
                    argument.putLong("order", orderId);
                    Navigation.findNavController(allOrdersActivity, R.id.nav_host_fragment).navigate(R.id.action_ordersFragment_to_orderDetails, argument);
                }
            }
        };
        binding.listView.setAdapter(recyclerAdapter);

        if (orderData.isEmpty())
        {
            binding.errorMessage.setText(getString(R.string.no_orders_available));
        }
    }
}