package com.ecxfoi.wbl.wienerbergerfrontend.ui.login;

import android.app.Activity;

import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.ecxfoi.wbl.wienerbergerfrontend.CompanySelectionActivity;
import com.ecxfoi.wbl.wienerbergerfrontend.R;
import com.ecxfoi.wbl.wienerbergerfrontend.data.Authenticator;
import com.ecxfoi.wbl.wienerbergerfrontend.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity
{
    private ActivityLoginBinding binding;

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private SwitchCompat passwordSwitch;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        if (getSupportActionBar() != null)
        {
            getSupportActionBar().hide();
        }

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        usernameEditText = binding.username;
        passwordEditText = binding.password;
        loginButton = binding.login;
        passwordSwitch = binding.passwordSwitch;

        String storedUsername = Authenticator.getUsername(this);
        String storedPassword = Authenticator.getPassword(this);

        if (storedUsername != null && storedPassword != null) {
            usernameEditText.setText(storedUsername);
            passwordEditText.setText(storedPassword);
            loginButton.setEnabled(true);
        }

        usernameEditText.addTextChangedListener(getUsernameTextWatcher());
        passwordEditText.addTextChangedListener(getPasswordTextWatcher());

        loginButton.setOnClickListener(v -> {
            boolean loginResult = Authenticator.login(usernameEditText.getText().toString(),
                    passwordEditText.getText().toString(), this);

            if (!loginResult)
            {
                showLoginFailed(R.string.login_failed);
                return;
            }

            setResult(Activity.RESULT_OK);

            finish();
            switchToCompanySelection();
        });

        passwordSwitch.setOnCheckedChangeListener(this::onCheckedChanged);
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

    private TextWatcher getUsernameTextWatcher() {
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
                if (usernameEditText.getText().length() > 2 && passwordEditText.getText().length() > 2) {
                    loginButton.setEnabled(true);
                } else if (loginButton.isEnabled()) {
                    loginButton.setEnabled(false);
                }
            }
        };
    }


    private TextWatcher getPasswordTextWatcher() {
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
                if (usernameEditText.getText().length() > 2 && passwordEditText.getText().length() > 2) {
                    loginButton.setEnabled(true);
                } else if (loginButton.isEnabled()) {
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

    private void showLoginFailed(@StringRes Integer errorString)
    {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}