package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.orders;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.ecxfoi.wbl.wienerbergerfrontend.R;
import com.ecxfoi.wbl.wienerbergerfrontend.base.BaseFragment;
import com.ecxfoi.wbl.wienerbergerfrontend.databinding.OrderDetailsFragmentBinding;
import com.ecxfoi.wbl.wienerbergerfrontend.models.OrderData;
import com.ecxfoi.wbl.wienerbergerfrontend.models.OrderDetailsData;

import javax.inject.Inject;

public class OrderDetailsFragment extends BaseFragment<OrderDetailsViewModel>
{
    @Inject
    ViewModelProvider.Factory factory;

    private OrderDetailsViewModel viewModel;

    @Override
    public OrderDetailsViewModel getViewModel()
    {
        viewModel = new ViewModelProvider(this, factory).get(OrderDetailsViewModel.class);
        return viewModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        final OrderDetailsFragmentBinding binding = OrderDetailsFragmentBinding.inflate(inflater, container, false);
        final Button buttonBack = binding.btnBack;

        binding.setOrderDetailsViewModel(viewModel);

        if (getArguments() == null)
        {
            Toast.makeText(getContext(), getString(R.string.generic_error_message), Toast.LENGTH_LONG).show();
        }

        final Activity allOrdersActivity = getActivity();
        if (allOrdersActivity != null)
        {
            buttonBack.setOnClickListener(v -> Navigation.findNavController(allOrdersActivity, R.id.nav_host_fragment).navigate(R.id.action_orderDetails_to_ordersFragment));
        }

        Long currentOrderID = getArguments().getLong("order");
        viewModel.fetchOrderData(currentOrderID).observe(getViewLifecycleOwner(), this::setOrderData);

        return binding.getRoot();
    }

    public void setOrderData(OrderDetailsData orderData)
    {
        viewModel.setOrderData(orderData);
    }
}