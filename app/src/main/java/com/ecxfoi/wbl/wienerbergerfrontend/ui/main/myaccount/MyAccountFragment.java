package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.myaccount;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.ecxfoi.wbl.wienerbergerfrontend.R;
import com.ecxfoi.wbl.wienerbergerfrontend.base.BaseFragment;
import com.ecxfoi.wbl.wienerbergerfrontend.databinding.FragmentMyAccountBinding;
import com.ecxfoi.wbl.wienerbergerfrontend.models.UserData;

import org.apache.commons.lang.StringUtils;

import javax.inject.Inject;

public class MyAccountFragment extends BaseFragment<MyAccountViewModel>
{
    @Inject
    ViewModelProvider.Factory factory;

    private MyAccountViewModel viewModel;
    private FragmentMyAccountBinding binding;

    @Override
    public MyAccountViewModel getViewModel()
    {
        viewModel = new ViewModelProvider(this, factory).get(MyAccountViewModel.class);
        return viewModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentMyAccountBinding.inflate(inflater, container, false);
        binding.setMyAccountViewModel(viewModel);

        initNavigation();

        viewModel.setMessagesFromResources(getString(R.string.error_check_field),
                getString(R.string.info_user_data_updated),
                getString(R.string.error_check_fields),
                getString(R.string.error_no_connection));

        viewModel.getCurrentUserData().observe(getViewLifecycleOwner(), this::setUserData);

        return binding.getRoot();
    }

    private void setUserData(UserData userData)
    {
        viewModel.setUserData(userData);
        initDropdown(userData.getTitle());
    }

    private void initNavigation()
    {
        binding.btnUpdate.setOnClickListener(v -> viewModel.updateUserData());
    }

    private void initDropdown(String currentUserTitle)
    {
        String[] titles = new String[]{"Mr.", "Mrs.", "Other"};

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(getActivity(), R.layout.spinner_item_grey, titles);
        binding.spinnerTitle.setAdapter(adapter);

        if (StringUtils.isNotEmpty(currentUserTitle))
        {
            int spinnerPosition = adapter.getPosition(currentUserTitle);

            if (spinnerPosition != -1)
            {
                binding.spinnerTitle.setSelection(spinnerPosition);
            }
        }
    }
}