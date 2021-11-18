package com.ecxfoi.wbl.wienerbergerfrontend.ui.login;

import android.app.Activity;

import androidx.appcompat.widget.SwitchCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.ecxfoi.wbl.wienerbergerfrontend.CompanySelectionActivity;
import com.ecxfoi.wbl.wienerbergerfrontend.R;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.login.LoginViewModel;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.login.LoginViewModelFactory;
import com.ecxfoi.wbl.wienerbergerfrontend.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity
{

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private SwitchCompat passwordSwitch;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); // will hide the title

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide(); // hide the title bar
        }

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); // enable full screen

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        usernameEditText = binding.username;
        passwordEditText = binding.password;
        loginButton = binding.login;
        passwordSwitch = binding.passwordSwitch;

        loginViewModel.getLoginFormState().observe(this, loginFormState -> {
            if (loginFormState == null)
            {
                return;
            }
            loginButton.setEnabled(loginFormState.isDataValid());
            if (loginFormState.getUsernameError() != null)
            {
                usernameEditText.setError(getString(loginFormState.getUsernameError()));
            }
            if (loginFormState.getPasswordError() != null)
            {
                passwordEditText.setError(getString(loginFormState.getPasswordError()));
            }
        });

        loginViewModel.getLoginResult().observe(this, loginResult -> {
            if (loginResult == null)
            {
                return;
            }

            if (loginResult.getError() != null)
            {
                showLoginFailed(loginResult.getError());
                return;
            }
            if (loginResult.getSuccess() != null)
            {
                updateUiWithUser(loginResult.getSuccess());
            }
            setResult(Activity.RESULT_OK);

            // Complete and destroy login activity once successful
            finish();

            switchToCompanySelection();
        });

        TextWatcher afterTextChangedListener = new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE)
            {
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
            return false;
        });

        loginButton.setOnClickListener(v -> loginViewModel.login(usernameEditText.getText().toString(),
                passwordEditText.getText().toString()));

        passwordSwitch.setOnCheckedChangeListener(this::onCheckedChanged);
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked) {
            passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    private void switchToCompanySelection() {
        Intent intent = new Intent(this, CompanySelectionActivity.class);
        startActivity(intent);
    }

    private void updateUiWithUser(LoggedInUserView model)
    {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString)
    {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}