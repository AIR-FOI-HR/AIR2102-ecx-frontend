package com.ecxfoi.wbl.pin_login;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

public class PinLoginViewModel extends ViewModel
{
    public ObservableField<String> tvErrorMessage;

    public PinLoginViewModel()
    {
        tvErrorMessage = new ObservableField<>("");
    }

    public void setErrorMessage(final String s)
    {
        tvErrorMessage.set(s);
    }
}