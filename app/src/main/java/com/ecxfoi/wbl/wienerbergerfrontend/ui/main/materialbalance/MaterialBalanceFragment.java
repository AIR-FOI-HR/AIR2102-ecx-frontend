package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.materialbalance;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ecxfoi.wbl.wienerbergerfrontend.R;
import com.ecxfoi.wbl.wienerbergerfrontend.base.BaseFragment;
import com.ecxfoi.wbl.wienerbergerfrontend.databinding.FragmentMaterialBalanceBinding;
import com.ecxfoi.wbl.wienerbergerfrontend.databinding.FragmentMyAccountBinding;
import com.ecxfoi.wbl.wienerbergerfrontend.models.MaterialBalanceData;
import com.ecxfoi.wbl.wienerbergerfrontend.repositories.MaterialBalanceRepository;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.home.HomeViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

public class MaterialBalanceFragment extends BaseFragment<MaterialBalanceViewModel>
{
    @Inject
    ViewModelProvider.Factory factory;

    private MaterialBalanceViewModel viewModel;
    private FragmentMaterialBalanceBinding binding;

    @Override
    public MaterialBalanceViewModel getViewModel()
    {
        viewModel = new ViewModelProvider(this, factory).get(MaterialBalanceViewModel.class);
        return viewModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentMaterialBalanceBinding.inflate(inflater, container, false);
        binding.setMaterialBalanceViewModel(viewModel);

        final Button buttonBack = binding.btnBack;

        final Activity allMaterialsActivity = getActivity();
        if (allMaterialsActivity != null)
        {
            buttonBack.setOnClickListener(v -> Navigation.findNavController(allMaterialsActivity, R.id.nav_host_fragment).navigate(R.id.action_materialBalanceFragment_to_homeFragment));
        }

        viewModel.fetchMaterialBalanceData().observe(getViewLifecycleOwner(), this::setMaterialBalanceData);

        return binding.getRoot();
    }

    private void setMaterialBalanceData(final ArrayList<MaterialBalanceData> materialBalanceData)
    {
        binding.listView.setLayoutManager(new LinearLayoutManager(getActivity()));
        MaterialBalanceRecyclerAdapter recyclerAdapter = new MaterialBalanceRecyclerAdapter(materialBalanceData)
        {

        };
        binding.listView.setAdapter(recyclerAdapter);
    }
}