package com.ecxfoi.wbl.wienerbergerfrontend.ui.login;

import androidx.lifecycle.ViewModel;

import com.ecxfoi.wbl.wienerbergerfrontend.auth.AuthService;

import javax.inject.Inject;

public class LoginViewModel extends ViewModel
{
    AuthService authService;

    @Inject
    public LoginViewModel(AuthService authService)
    {
        this.authService = authService;
    }
}
