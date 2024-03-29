package com.ecxfoi.wbl.pin_login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ecxfoi.wbl.interface_login.LoginFragment;
import com.ecxfoi.wbl.pin_login.databinding.PinLoginFragmentBinding;

public class PinLoginFragment extends Fragment implements LoginFragment
{
    public static final int maxAttempts = 3;
    Listener mListener;
    private int attemptCount = 1;
    private PinLoginFragmentBinding binding;
    private PinLoginViewModel mViewModel;

    public static PinLoginFragment newInstance()
    {
        return new PinLoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        binding = PinLoginFragmentBinding.inflate(inflater, container, false);

        initPinCheck();

        mViewModel = new ViewModelProvider(this).get(PinLoginViewModel.class);
        binding.setViewModel(mViewModel);

        return binding.getRoot();
    }

    private void initPinCheck()
    {
        binding.pinTextBox.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(final CharSequence charSequence, final int i, final int i1, final int i2)
            {
            }

            @Override
            public void afterTextChanged(final Editable editable)
            {
            }

            @Override
            public void onTextChanged(final CharSequence charSequence, final int start, final int before, final int count)
            {
                if (charSequence.length() == 4)
                {
                    isInputAvaliable(false);
                    mListener.onLoginAttempt(charSequence.toString());
                }
            }
        });
    }

    @Override
    public void setErrorMessage(final String errorMessage)
    {
        attemptCount++;
        if (attemptCount > maxAttempts)
        {
            mListener.onMultipleFailedAttempts();
            return;
        }
        mViewModel.setErrorMessage(errorMessage + ": " + attemptCount + "/" + maxAttempts);
        isInputAvaliable(true);
    }

    @Override
    public <T> void setListener(final T listener)
    {
        mListener = (PinLoginFragment.Listener) listener;
    }

    private void isInputAvaliable(final boolean available)
    {
        if (!available) mViewModel.setInfoMessage("Please wait");
        else binding.pinTextBox.setText("");
        binding.pinTextBox.setEnabled(available);
    }

    public interface Listener
    {
        void onLoginAttempt(String PIN);

        void onMultipleFailedAttempts();
    }
}