package com.ecxfoi.wbl.fingerprint_login;

import android.app.Activity;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.ecxfoi.wbl.fingerprint_login.databinding.FingerprintLoginFragmentBinding;
import com.ecxfoi.wbl.interface_login.LoginFragment;

import java.util.concurrent.Executor;


public class FingerprintLoginFragment extends Fragment implements LoginFragment
{
    private FingerprintLoginFragmentBinding binding;
    private FingerprintLoginViewModel viewModel;

    public interface Listener
    {
        void onLoginAttempt(boolean success, int errorCode);
    }

    private Listener listener;

    @Override
    public <T> void setListener(final T listener)
    {
        this.listener = (FingerprintLoginFragment.Listener) listener;
    }

    @Override
    public void setErrorMessage(final String errorMessage)
    {

    }

    public static FingerprintLoginFragment newInstance()
    {
        return new FingerprintLoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        binding = FingerprintLoginFragmentBinding.inflate(inflater, container, false);

        initFingerprint();

        return binding.getRoot();
    }

    private void initFingerprint()
    {
        final Activity activity = getActivity();

        if (activity == null)
        {
            return;
        }

        final Executor executor = ContextCompat.getMainExecutor(activity);
        new BiometricPrompt.Builder(activity)
                .setTitle(getString(R.string.biometric_prompt_title))
                .setDescription(getString(R.string.biometric_prompt_description))
                .setNegativeButton(getString(R.string.biometric_prompt_cancel_text), executor,
                        (dialog, which) -> {
                            listener.onLoginAttempt(false, -1);
                        }
                )
                .build()
                .authenticate(new CancellationSignal(), executor,
                        new BiometricPrompt.AuthenticationCallback()
                        {
                            @Override
                            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString)
                            {
                                super.onAuthenticationError(errorCode, errString);

                                listener.onLoginAttempt(false, errorCode);
                            }

                            @Override
                            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result)
                            {
                                super.onAuthenticationSucceeded(result);
                                listener.onLoginAttempt(true, -1);
                            }

                            @Override
                            public void onAuthenticationFailed()
                            {
                                super.onAuthenticationFailed();
                            }
                        });
    }
}
