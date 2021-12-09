package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.materialbalance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.ecxfoi.wbl.wienerbergerfrontend.R;
import com.ecxfoi.wbl.wienerbergerfrontend.base.BaseFragment;
import com.ecxfoi.wbl.wienerbergerfrontend.databinding.FragmentMaterialBalanceBinding;
import com.ecxfoi.wbl.wienerbergerfrontend.databinding.FragmentMyAccountBinding;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.home.HomeViewModel;

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

        return binding.getRoot();
    }
}