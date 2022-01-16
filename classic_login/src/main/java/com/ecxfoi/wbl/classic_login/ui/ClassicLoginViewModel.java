package com.ecxfoi.wbl.classic_login.ui;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import java.util.Observable;

public class ClassicLoginViewModel extends ViewModel
{
    public ObservableField<String> emailEditText;
    public ObservableField<String> passwordEditText;
    public ObservableField<String> errorMessage;
}