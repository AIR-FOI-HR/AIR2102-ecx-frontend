package com.ecxfoi.wbl.classic_login.ui;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

public class ClassicLoginViewModel extends ViewModel
{
    public final ObservableField<String> emailText;
    public final ObservableField<String> passwordText;
    public final ObservableField<String> errorMessageText;

    public ClassicLoginViewModel()
    {
        emailText = new ObservableField<>();
        passwordText = new ObservableField<>();
        errorMessageText = new ObservableField<>();
    }

    public String getEmail()
    {
        return emailText.get();
    }

    public void setEmail(final String email)
    {
        emailText.set(email);
    }

    public String getPassword()
    {
        return passwordText.get();
    }

    public void setPassword(final String password)
    {
        passwordText.set(password);
    }

    public String getErrorMessage()
    {
        return errorMessageText.get();
    }

    public void setErrorMessage(final String errorMessage)
    {
        errorMessageText.set(errorMessage);
    }
}