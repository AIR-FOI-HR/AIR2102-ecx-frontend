package com.ecxfoi.wbl.classic_login.ui;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecxfoi.wbl.classic_login.R;
import com.ecxfoi.wbl.classic_login.databinding.ClassicLoginFragmentBinding;

public class ClassicLoginFragment extends Fragment
{
    private ClassicLoginFragmentBinding binding;
    private ClassicLoginViewModel mViewModel;

    public static ClassicLoginFragment newInstance()
    {
        return new ClassicLoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        binding = ClassicLoginFragmentBinding.inflate(inflater, container, false);

        mViewModel = new ViewModelProvider(this).get(ClassicLoginViewModel.class);
        // TODO: Use the ViewModel

        return binding.getRoot();
    }
}