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

public class ClassicLoginFragment extends Fragment
{

    private ClassicLoginViewModel mViewModel;

    public static ClassicLoginFragment newInstance()
    {
        return new ClassicLoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.classic_login_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ClassicLoginViewModel.class);
        // TODO: Use the ViewModel
    }
}