package com.ecxfoi.wbl.wienerbergerfrontend.ui.login;

import android.app.Activity;

import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.StringRes;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ecxfoi.wbl.wienerbergerfrontend.api.JwtAuthInterceptor;
import com.ecxfoi.wbl.wienerbergerfrontend.base.BaseActivity;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.companyselection.CompanySelectionActivity;
import com.ecxfoi.wbl.wienerbergerfrontend.R;
import com.ecxfoi.wbl.wienerbergerfrontend.auth.AuthService;
import com.ecxfoi.wbl.wienerbergerfrontend.auth.AuthenticationData;
import com.ecxfoi.wbl.wienerbergerfrontend.auth.AuthenticationInterface;
import com.ecxfoi.wbl.wienerbergerfrontend.databinding.ActivityLoginBinding;
import com.ecxfoi.wbl.wienerbergerfrontend.models.WienerbergerResponse;
import com.google.android.material.navigation.NavigationView;

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

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView errorMessage;

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

        initBindings();
        initNavigation();

        retreiveStoredUserData();


        AuthService.authenticationInterface = new AuthenticationInterface()
        {
            @Override
            public void interpretResponse(final String email, final String password, final Response<WienerbergerResponse<AuthenticationData>> response)
            {
                changeInputEnableness(true);

                WienerbergerResponse<AuthenticationData> wienerbergerResponse;
                if (response.isSuccessful())
                {
                    wienerbergerResponse = response.body();
                    AuthenticationData responseData = wienerbergerResponse.getData();
                    String jwt = responseData.jwt;

                    rememberUser(email, password);

                    setResult(Activity.RESULT_OK);

//                    showLoginSuccess(R.string.welcome);
                    finish();
                    switchToCompanySelection();

                    authInterceptor.setJwtToken(jwt);
                }
                else
                {
                    int errorString = R.string.login_failed_generic;

                    try
                    {
                        // Casting to real objects just didn't work so easier variant was implemented here:
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());

                        errorString = R.string.login_failed_credentials;
                    }
                    catch (JSONException | IOException e)
                    {
                        e.printStackTrace();
                    }

                    showLoginFailed(errorString);
                }
            }

            @Override
            public void interpretError()
            {
                showLoginFailed(R.string.error_no_connection);
            }
        };

        loginButton.setOnClickListener(v -> attemptLogin());
    }

    private void initNavigation()
    {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_login);

        if (navHostFragment != null)
        {
            navController = navHostFragment.getNavController();

            AppBarConfiguration appBarConfiguration =
                    new AppBarConfiguration.Builder(navController.getGraph()).build();
        }
    }

    private void initBindings()
    {
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        emailEditText = binding.email;
//        passwordEditText = binding.password;
//        loginButton = binding.login;
//        errorMessage = binding.errorMessage;
//        final SwitchCompat passwordSwitch = binding.passwordSwitch;

        emailEditText.addTextChangedListener(getEmailTextWatcher());
        passwordEditText.addTextChangedListener(getPasswordTextWatcher());
//        passwordSwitch.setOnCheckedChangeListener(this::onCheckedChanged);
    }

    private void rememberUser(String email, String password)
    {
        AuthService.setEmail(email, this);
        AuthService.setPassword(password, this);
    }

    private void retreiveStoredUserData()
    {
        String storedEmail = AuthService.getEmail(this);
        String storedPassword = AuthService.getPassword(this);

        if (storedEmail != null && storedPassword != null)
        {
            emailEditText.setText(storedEmail);
            passwordEditText.setText(storedPassword);
            loginButton.setEnabled(true);
        }
    }

    private void attemptLogin()
    {
        errorMessage.setText("");
        changeInputEnableness(false);
        try
        {
            String enteredEmail = emailEditText.getText().toString();

            if (!StringUtils.contains(enteredEmail, '@'))
            {
                enteredEmail += "@wb.com";
                emailEditText.setText(enteredEmail);
            }

            String enteredPassword = passwordEditText.getText().toString();
            AuthService.createLoginRequest(enteredEmail, enteredPassword);
        }
        catch (Exception e)
        {
            this.showLoginFailed(R.string.error_no_connection);
        }
    }

    private void changeInputEnableness(boolean disable)
    {
        loginButton.setEnabled(disable);
        emailEditText.setEnabled(disable);
        passwordEditText.setEnabled(disable);
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
    {
        if (isChecked)
        {
            passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        else
        {
            passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    private TextWatcher getEmailTextWatcher()
    {
        return new TextWatcher()
        {
            @Override
            public void beforeTextChanged(final CharSequence charSequence, final int i, final int i1, final int i2)
            {
                // ignore
            }

            @Override
            public void onTextChanged(final CharSequence charSequence, final int i, final int i1, final int i2)
            {
                // ignore
            }

            @Override
            public void afterTextChanged(final Editable editable)
            {
                if (emailEditText.getText().length() > 2 && passwordEditText.getText().length() > 2)
                {
                    loginButton.setEnabled(true);
                }
                else if (loginButton.isEnabled())
                {
                    loginButton.setEnabled(false);
                }
            }
        };
    }


    private TextWatcher getPasswordTextWatcher()
    {
        return new TextWatcher()
        {
            @Override
            public void beforeTextChanged(final CharSequence charSequence, final int i, final int i1, final int i2)
            {
                // ignore
            }

            @Override
            public void onTextChanged(final CharSequence charSequence, final int i, final int i1, final int i2)
            {
                // ignore
            }

            @Override
            public void afterTextChanged(final Editable editable)
            {
                if (emailEditText.getText().length() > 2 && passwordEditText.getText().length() > 2)
                {
                    loginButton.setEnabled(true);
                }
                else if (loginButton.isEnabled())
                {
                    loginButton.setEnabled(false);
                }
            }
        };
    }

    private void switchToCompanySelection()
    {
        Intent intent = new Intent(this, CompanySelectionActivity.class);
        startActivity(intent);
    }

    private void showLoginSuccess(@StringRes Integer welcomeMessage)
    {
        Toast.makeText(getApplicationContext(), getString(welcomeMessage) + emailEditText.getText(), Toast.LENGTH_SHORT).show();
    }

    private void showLoginFailed(@StringRes Integer errorString)
    {
        errorMessage.setText(errorString);
    }
}