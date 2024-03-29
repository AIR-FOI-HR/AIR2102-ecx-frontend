package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.settings;

import static com.ecxfoi.wbl.wienerbergerfrontend.utils.SettingsManager.LoginMethods.CLASSIC;
import static com.ecxfoi.wbl.wienerbergerfrontend.utils.SettingsManager.LoginMethods.NONE;
import static com.ecxfoi.wbl.wienerbergerfrontend.utils.SettingsManager.LoginMethods.PIN;

import android.content.Context;
import android.widget.AdapterView;
import android.widget.CompoundButton;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.ecxfoi.wbl.wienerbergerfrontend.utils.SettingsManager;

import java.util.Objects;

import javax.inject.Inject;

public class SettingsViewModel extends ViewModel
{
    public final ObservableField<Boolean> doRememberLogin;
    public final ObservableField<Boolean> isPinOptionSelected;
    public final ObservableField<Boolean> isPinEntered;
    public final ObservableField<Integer> selectedItemIndex;
    public final ObservableField<String> pin;
    public final ObservableField<AdapterView.OnItemSelectedListener> arrayListAdapter;

    @Inject
    public SettingsViewModel()
    {
        doRememberLogin = new ObservableField<>(false);
        isPinOptionSelected = new ObservableField<>(false);
        isPinEntered = new ObservableField<>(false);
        selectedItemIndex = new ObservableField<>();
        pin = new ObservableField<>("");
        arrayListAdapter = new ObservableField<>();
    }

    public void onRememberLogin(final CompoundButton compoundButton, final boolean b)
    {
        setDoRememberLogin(b ? CLASSIC : NONE, compoundButton.getContext());
    }

    public void setDoRememberLogin(final SettingsManager.LoginMethods method, Context context)
    {
        boolean condition = method != PIN || !Objects.requireNonNull(pin.get()).isEmpty();
        if (condition)
        {
            SettingsManager.setRememberLogin(method, context); // PIN is remembered only if it's entered.
        }

        showSelectedOption(method);
    }

    private void showSelectedOption(final SettingsManager.LoginMethods method)
    {
        doRememberLogin.set(method != NONE);
        isPinOptionSelected.set(method == PIN);
        selectedItemIndex.set(getMethodSpinnerIndex(method));
    }

    private Integer getMethodSpinnerIndex(final SettingsManager.LoginMethods method)
    {
        return method.ordinal() - 1; // Decrement because indexes in list go by 0, and that enum value is NULL.
    }
}