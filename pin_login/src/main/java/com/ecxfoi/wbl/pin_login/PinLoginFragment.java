package com.ecxfoi.wbl.pin_login;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecxfoi.wbl.interface_login.LoginFragment;

public class PinLoginFragment extends Fragment implements LoginFragment
{

    private PinLoginViewModel mViewModel;

    public static PinLoginFragment newInstance()
    {
        return new PinLoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.pin_login_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PinLoginViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void setErrorMessage(final String errorMessage)
    {
        isInputAvaliable(true);
    }

    @Override
    public <T> void setListener(final T listener)
    {

    }

    private void isInputAvaliable(final boolean b)
    {
    }
}