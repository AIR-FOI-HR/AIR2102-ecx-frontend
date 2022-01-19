package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.settings;

import static com.ecxfoi.wbl.wienerbergerfrontend.utils.SettingsManager.LoginMethods.CLASSIC;

import android.widget.AdapterView;
import android.widget.CompoundButton;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.ecxfoi.wbl.wienerbergerfrontend.utils.SettingsManager;

import javax.inject.Inject;

public class SettingsViewModel extends ViewModel
{
    public ObservableField<Boolean> rememberLogin;
    public ObservableField<Integer> selectedItemIndex;
    public ObservableField<AdapterView.OnItemSelectedListener> arrayListAdapter;

    @Inject
    public SettingsViewModel()
    {
        rememberLogin = new ObservableField<>(false);
        selectedItemIndex = new ObservableField<>();
        arrayListAdapter = new ObservableField<>();
    }

    public void setRememberLogin(final CompoundButton compoundButton, final boolean b)
    {
        setRememberLogin(b);
        SettingsManager.setRememberLogin(b ? CLASSIC : SettingsManager.LoginMethods.NONE, compoundButton.getContext());
        if (b)
        {
            selectedItemIndex.set(0);
        }
    }

    public void setRememberLogin(final boolean remembered)
    {
        rememberLogin.set(remembered);
    }
}