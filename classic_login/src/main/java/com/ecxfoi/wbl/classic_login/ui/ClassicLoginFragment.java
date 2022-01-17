package com.ecxfoi.wbl.classic_login.ui;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.method.HideReturnsTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ecxfoi.wbl.classic_login.databinding.ClassicLoginFragmentBinding;
import com.ecxfoi.wbl.interface_login.LoginFragment;

public class ClassicLoginFragment extends Fragment implements LoginFragment
{
    private ClassicLoginFragmentBinding binding;
    private ClassicLoginViewModel mViewModel;

    @Override
    public void setErrorMessage(final String errorMessage)
    {
        isInputAvaliable(true);
        binding.errorMessage.setText(errorMessage);
    }

    public interface LoginListener
    {
        void onLoginAttempt(String email, String password);
    }

    private LoginListener mListener;

    @Override
    public <T> void setListener(final T listener)
    {
        mListener = (ClassicLoginFragment.LoginListener) listener;
    }

    private void isInputAvaliable(boolean enabled)
    {
        binding.email.setEnabled(enabled);
        binding.password.setEnabled(enabled);
        binding.passwordSwitch.setEnabled(enabled);
    }

    public static ClassicLoginFragment newInstance()
    {
        return new ClassicLoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        binding = ClassicLoginFragmentBinding.inflate(inflater, container, false);

        initNavigation();

        mViewModel = new ViewModelProvider(this).get(ClassicLoginViewModel.class);

        return binding.getRoot();
    }

    private void initNavigation()
    {
        binding.passwordSwitch.setOnCheckedChangeListener((compoundButton, isChecked) -> binding.password.setTransformationMethod(isChecked ? HideReturnsTransformationMethod.getInstance() : HideReturnsTransformationMethod.getInstance()));
        binding.login.setOnClickListener(v -> {
            if (binding.email.getText().toString().equals(""))
            {
                Toast.makeText(getContext(), "Please enter your email.", Toast.LENGTH_SHORT).show();
                return;
            }
            else if (binding.password.getText().toString().equals(""))
            {
                Toast.makeText(getContext(), "Please enter your password.", Toast.LENGTH_SHORT).show();
                return;
            }

            binding.errorMessage.setText("");
            isInputAvaliable(false);
            mListener.onLoginAttempt(binding.email.getText().toString(), binding.password.getText().toString());
        });
    }
}