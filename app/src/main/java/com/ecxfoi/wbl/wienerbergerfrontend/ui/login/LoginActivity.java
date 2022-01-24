package com.ecxfoi.wbl.wienerbergerfrontend.ui.login;

import android.accounts.AuthenticatorException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ecxfoi.wbl.classic_login.ui.ClassicLoginFragment;
import com.ecxfoi.wbl.fingerprint_login.FingerprintLoginFragment;
import com.ecxfoi.wbl.interface_login.LoginFragment;
import com.ecxfoi.wbl.pin_login.PinLoginFragment;
import com.ecxfoi.wbl.wienerbergerfrontend.api.JwtAuthInterceptor;
import com.ecxfoi.wbl.wienerbergerfrontend.auth.AuthenticationData;
import com.ecxfoi.wbl.wienerbergerfrontend.auth.AuthenticationInterface;
import com.ecxfoi.wbl.wienerbergerfrontend.base.BaseActivity;
import com.ecxfoi.wbl.wienerbergerfrontend.models.WienerbergerResponse;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.companyselection.CompanySelectionActivity;
import com.ecxfoi.wbl.wienerbergerfrontend.R;
import com.ecxfoi.wbl.wienerbergerfrontend.auth.AuthService;
import com.ecxfoi.wbl.wienerbergerfrontend.databinding.ActivityLoginBinding;
import com.ecxfoi.wbl.wienerbergerfrontend.utils.SettingsManager;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Response;

public class LoginActivity extends BaseActivity<LoginViewModel>
{
    private ActivityLoginBinding binding;

    private LoginFragment destFragment;

    @Inject
    LoginViewModel viewModel;

    @Inject
    JwtAuthInterceptor authInterceptor;
    @Inject
    ViewModelProvider.Factory factory;

    @Override
    public LoginViewModel getViewModel()
    {
        return viewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AuthService.authenticationInterface = new AuthenticationInterface()
        {
            @Override
            public void interpretResponse(final String email, final String password, final Response<WienerbergerResponse<AuthenticationData>> response)
            {
                WienerbergerResponse<AuthenticationData> wienerbergerResponse;
                if (response.isSuccessful())
                {
                    wienerbergerResponse = response.body();
                    AuthenticationData responseData = wienerbergerResponse.getData();
                    String jwt = responseData.jwt;

                    rememberUser(email, password);

                    setResult(Activity.RESULT_OK);

                    finish();
                    switchToCompanySelection();

                    authInterceptor.setJwtToken(jwt);
                }
                else
                {
                    int errorResCode = R.string.login_failed_generic;

                    try
                    {
                        // Casting to real objects just didn't work so easier variant was implemented here:
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());

                        errorResCode = R.string.login_failed_credentials;
                    }
                    catch (JSONException | IOException e)
                    {
                        e.printStackTrace();
                    }

                    destFragment.setErrorMessage(getResources().getString(errorResCode));
                }
            }

            @Override
            public void interpretError()
            {
                destFragment.setErrorMessage(getResources().getString(R.string.error_no_connection));
            }
        };

        SettingsManager.LoginMethods loginMethod = SettingsManager.getRememberLogin(getApplicationContext());

        navigateTo(loginMethod);
    }

    private void navigateTo(SettingsManager.LoginMethods destination)
    {
        if (destFragment != null)
            getSupportFragmentManager().beginTransaction().remove((Fragment) destFragment).commit();

        if (destination == SettingsManager.LoginMethods.FINGERPRINT && !SettingsManager.isFingerprintAvailable(this))
        {
            SettingsManager.setRememberLogin(SettingsManager.LoginMethods.NONE, this);
        }

        switch (destination)
        {
            case NONE:
                prepareClassicLoginFragment();
                break;
            case CLASSIC:
                prepareClassicLoginFragment();
                ((ClassicLoginFragment) destFragment).setEmailAndPassword(AuthService.getEmail(getApplicationContext()), AuthService.getPassword(getApplicationContext()));
                break;
            case PIN:
                preparePinLoginFragment();
                break;
            case FINGERPRINT:
                prepareFingerprintLoginFragment();
                break;
        }

        getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment_login, (Fragment) destFragment).commit();
    }

    private void prepareFingerprintLoginFragment()
    {
        destFragment = FingerprintLoginFragment.newInstance();

        destFragment.<FingerprintLoginFragment.Listener>setListener(success -> {
            try
            {
                if (success)
                {
                    AuthService.createLoginRequest(AuthService.getEmail(LoginActivity.this), AuthService.getPassword(LoginActivity.this));
                }
                else
                {
                    navigateTo(SettingsManager.LoginMethods.NONE);
                }
            }
            catch (Exception e)
            {
                destFragment.setErrorMessage(getResources().getString(R.string.error_no_connection));
            }
        });
    }

    private void prepareClassicLoginFragment()
    {
        destFragment = ClassicLoginFragment.newInstance();

        destFragment.<ClassicLoginFragment.LoginListener>setListener((String email, String password) -> {
            try
            {
                if (!StringUtils.contains(email, '@'))
                {
                    email += "@wb.com";
                }

                AuthService.createLoginRequest(email, password);
            }
            catch (Exception e)
            {
                destFragment.setErrorMessage(getResources().getString(R.string.error_no_connection));
            }
        });
    }

    private void preparePinLoginFragment()
    {
        destFragment = PinLoginFragment.newInstance();

        destFragment.<PinLoginFragment.Listener>setListener(new PinLoginFragment.Listener()
        {
            @Override
            public void onLoginAttempt(final String PIN)
            {
                try
                {
                    String actualPIN = AuthService.getPIN(getApplicationContext());

                    if (!StringUtils.equals(PIN, actualPIN))
                    {
                        throw new AuthenticatorException();
                    }

                    AuthService.createLoginRequest(AuthService.getEmail(getApplicationContext()), AuthService.getPassword(getApplicationContext()));
                }
                catch (Exception e)
                {
                    destFragment.setErrorMessage(getResources().getString(R.string.failed_pin_login_attempt));
                }
            }

            @Override
            public void onMultipleFailedAttempts()
            {
                SettingsManager.setRememberLogin(SettingsManager.LoginMethods.NONE, getApplicationContext());
                navigateTo(SettingsManager.LoginMethods.NONE);
            }
        });
    }

    private void rememberUser(String email, String password)
    {
        AuthService.setEmail(email, this);
        AuthService.setPassword(password, this);
    }

    private void switchToCompanySelection()
    {
        finishAndRemoveTask();
        Intent intent = new Intent(this, CompanySelectionActivity.class);
        startActivity(intent);
    }
}