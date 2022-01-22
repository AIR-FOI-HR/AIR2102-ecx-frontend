package com.ecxfoi.wbl.fingerprint_login;

import android.app.Activity;
import android.content.DialogInterface;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.biometric.BiometricManager;

import com.ecxfoi.wbl.fingerprint_login.databinding.FingerprintLoginFragmentBinding;
import com.ecxfoi.wbl.interface_login.LoginFragment;

import java.util.concurrent.Executor;


public class FingerprintLoginFragment extends Fragment implements LoginFragment
{
    private FingerprintLoginFragmentBinding binding;
    private FingerprintLoginViewModel viewModel;

    private Executor executor;
    private Activity activity;

    public interface Listener
    {
        void onLoginAttempt(boolean success);
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
        activity = getActivity();

        if (activity == null)
        {
            return;
        }

        executor = ContextCompat.getMainExecutor(activity);
        new BiometricPrompt.Builder(activity)
                .setTitle("Wienerberger")
                .setDescription("Please be so kind as to lightly tap your fingerprint sensor")
                .setNegativeButton("Cancel", executor,
                        (dialog, which) -> {
                            listener.onLoginAttempt(false);
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
                                listener.onLoginAttempt(false);
                            }

                            @Override
                            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result)
                            {
                                super.onAuthenticationSucceeded(result);
                                listener.onLoginAttempt(true);
                            }

                            @Override
                            public void onAuthenticationFailed()
                            {
                                super.onAuthenticationFailed();
                                listener.onLoginAttempt(false);
                            }
                        });
    }
}
