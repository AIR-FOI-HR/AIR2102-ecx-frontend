package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.materialbalance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ecxfoi.wbl.wienerbergerfrontend.R;
import com.ecxfoi.wbl.wienerbergerfrontend.base.BaseFragment;
import com.ecxfoi.wbl.wienerbergerfrontend.databinding.FragmentMaterialBalanceBinding;
import com.ecxfoi.wbl.wienerbergerfrontend.models.MaterialBalanceData;

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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentMaterialBalanceBinding.inflate(inflater, container, false);
        binding.setMaterialBalanceViewModel(viewModel);

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

        if (materialBalanceData.isEmpty())
        {
            viewModel.setErrorMessage(getString(R.string.no_materials_found));
        }
    }
}