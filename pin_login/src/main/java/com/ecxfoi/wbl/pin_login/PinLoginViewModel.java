package com.ecxfoi.wbl.pin_login;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

public class PinLoginViewModel extends ViewModel
{
    public final ObservableField<Boolean> error;
    public final ObservableField<String> tvMessage;

    public PinLoginViewModel()
    {
        error = new ObservableField<>(false);
        tvMessage = new ObservableField<>("");
    }

    public void setErrorMessage(final String errorMessage)
    {
        error.set(true);
        tvMessage.set(errorMessage);
    }

    public void setInfoMessage(final String infoMessage)
    {
        error.set(false);
        tvMessage.set(infoMessage);
    }
}