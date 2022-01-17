package com.ecxfoi.wbl.wienerbergerfrontend.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.ecxfoi.wbl.classic_login.ui.ClassicLoginFragment;
import com.ecxfoi.wbl.interface_login.LoginFragment;
import com.ecxfoi.wbl.wienerbergerfrontend.api.JwtAuthInterceptor;
import com.ecxfoi.wbl.wienerbergerfrontend.auth.AuthenticationData;
import com.ecxfoi.wbl.wienerbergerfrontend.auth.AuthenticationInterface;
import com.ecxfoi.wbl.wienerbergerfrontend.base.BaseActivity;
import com.ecxfoi.wbl.wienerbergerfrontend.models.WienerbergerResponse;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.companyselection.CompanySelectionActivity;
import com.ecxfoi.wbl.wienerbergerfrontend.R;
import com.ecxfoi.wbl.wienerbergerfrontend.auth.AuthService;
import com.ecxfoi.wbl.wienerbergerfrontend.databinding.ActivityLoginBinding;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Response;

public class LoginActivity extends BaseActivity<LoginViewModel>
{
    private ActivityLoginBinding binding;

    private NavController navController;

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

        navigateTo("classic");
    }

    private void navigateTo(String destination)
    {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        switch (destination)
        {
            case "classic":
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
                break;
            case "pin":
                break;
            case "fingerprint":
                break;
            default:
                break;
        }

        ft.add(R.id.nav_host_fragment_login, (Fragment) destFragment);
        ft.commit();
    }

    private void rememberUser(String email, String password)
    {
        AuthService.setEmail(email, this);
        AuthService.setPassword(password, this);
    }

    private void switchToCompanySelection()
    {
        Intent intent = new Intent(this, CompanySelectionActivity.class);
        startActivity(intent);
    }
}