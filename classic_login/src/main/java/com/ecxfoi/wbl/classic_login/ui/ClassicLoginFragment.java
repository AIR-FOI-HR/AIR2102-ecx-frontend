package com.ecxfoi.wbl.classic_login.ui;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ecxfoi.wbl.classic_login.R;
import com.ecxfoi.wbl.classic_login.databinding.ClassicLoginFragmentBinding;
import com.ecxfoi.wbl.interface_login.LoginFragment;

import java.util.Objects;

public class ClassicLoginFragment extends Fragment implements LoginFragment
{
    private ClassicLoginFragmentBinding binding;
    private ClassicLoginViewModel mViewModel;

    private String email = "";
    private String password = "";

    public void setEmailAndPassword(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    @Override
    public void setErrorMessage(final String errorMessage)
    {
        isInputAvaliable(true);
        mViewModel.setErrorMessage(errorMessage);
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
        binding.login.setEnabled(enabled);
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
        binding.setClassicLoginViewModel(mViewModel);

        mViewModel.setEmail(this.email);
        mViewModel.setPassword(this.password);

        return binding.getRoot();
    }

    private void initNavigation()
    {
        binding.passwordSwitch.setOnCheckedChangeListener(
                (compoundButton, isChecked) -> binding.password.setTransformationMethod(
                        isChecked
                                ? HideReturnsTransformationMethod.getInstance() :
                                PasswordTransformationMethod.getInstance()));

        binding.login.setOnClickListener(v -> {
            if (Objects.equals(mViewModel.getEmail(), ""))
            {
                Toast.makeText(getContext(), R.string.email_missing, Toast.LENGTH_SHORT).show();
                return;
            }
            else if (Objects.equals(mViewModel.getPassword(), ""))
            {
                Toast.makeText(getContext(), R.string.password_missing, Toast.LENGTH_SHORT).show();
                return;
            }

            mViewModel.setErrorMessage("");
            isInputAvaliable(false);
            mListener.onLoginAttempt(binding.email.getText().toString(), binding.password.getText().toString());
        });
    }
}