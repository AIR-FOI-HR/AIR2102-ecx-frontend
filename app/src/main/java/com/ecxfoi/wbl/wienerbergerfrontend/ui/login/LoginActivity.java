package com.ecxfoi.wbl.wienerbergerfrontend.ui.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;

import com.ecxfoi.wbl.classic_login.ui.ClassicLoginFragment;
import com.ecxfoi.wbl.wienerbergerfrontend.api.JwtAuthInterceptor;
import com.ecxfoi.wbl.wienerbergerfrontend.base.BaseActivity;
import com.ecxfoi.wbl.wienerbergerfrontend.databinding.ActivityMainBinding;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.companyselection.CompanySelectionActivity;
import com.ecxfoi.wbl.wienerbergerfrontend.R;
import com.ecxfoi.wbl.wienerbergerfrontend.auth.AuthService;
import com.ecxfoi.wbl.wienerbergerfrontend.databinding.ActivityLoginBinding;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity<LoginViewModel>
{
    private ActivityLoginBinding binding;

    private NavController navController;

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

        navigateTo("classic");
    }

    private void navigateTo(String destination)
    {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment destFragment = new Fragment();

        switch(destination)
        {
            case "classic":
                destFragment = ClassicLoginFragment.newInstance();
                break;
            case "pin":
                break;
            case "fingerprint":
                break;
            default: break;
        }

        ft.add(R.id.nav_host_fragment_login, destFragment);
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