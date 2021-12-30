package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.ecxfoi.wbl.wienerbergerfrontend.R;
import com.ecxfoi.wbl.wienerbergerfrontend.base.BaseFragment;
import com.ecxfoi.wbl.wienerbergerfrontend.databinding.FragmentHomeBinding;

import javax.inject.Inject;

public class HomeFragment extends BaseFragment<HomeViewModel>
{
    @Inject
    ViewModelProvider.Factory factory;

    private HomeViewModel viewModel;
    private FragmentHomeBinding binding;

    @Override
    public HomeViewModel getViewModel()
    {
        viewModel = new ViewModelProvider(this, factory).get(HomeViewModel.class);
        return viewModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        final ImageView ivOrders = binding.ivOrders;
        final ImageView ivMaterialBalance = binding.ivMaterialBalanceView;
        final ImageView ivSupportTickets = binding.ivSupportTickets;
        final ImageView ivDeliveryNotes = binding.ivDeliveryNotes;

        final Activity mainActivity = getActivity();
        if (mainActivity != null)
        {
            ivDeliveryNotes.setOnClickListener(v -> Navigation.findNavController(mainActivity, R.id.nav_host_fragment).navigate(R.id.action_homeFragment_to_deliveryNotesFragment));

            ivMaterialBalance.setOnClickListener(v -> Navigation.findNavController(mainActivity, R.id.nav_host_fragment).navigate(R.id.action_homeFragment_to_materialBalanceFragment));

            ivOrders.setOnClickListener(v -> Navigation.findNavController(mainActivity, R.id.nav_host_fragment).navigate(R.id.action_homeFragment_to_ordersFragment));

            ivSupportTickets.setOnClickListener(v -> Navigation.findNavController(mainActivity, R.id.nav_host_fragment).navigate(R.id.action_homeFragment_to_supportTicketsFragment));
        }

        return binding.getRoot();
    }
}