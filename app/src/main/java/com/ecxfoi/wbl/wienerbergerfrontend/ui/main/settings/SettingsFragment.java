package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.settings;

import static com.ecxfoi.wbl.wienerbergerfrontend.utils.SettingsManager.LoginMethods.CLASSIC;
import static com.ecxfoi.wbl.wienerbergerfrontend.utils.SettingsManager.LoginMethods.FINGERPRINT;
import static com.ecxfoi.wbl.wienerbergerfrontend.utils.SettingsManager.LoginMethods.NONE;
import static com.ecxfoi.wbl.wienerbergerfrontend.utils.SettingsManager.LoginMethods.PIN;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.ecxfoi.wbl.wienerbergerfrontend.R;
import com.ecxfoi.wbl.wienerbergerfrontend.base.BaseFragment;
import com.ecxfoi.wbl.wienerbergerfrontend.databinding.SettingsFragmentBinding;
import com.ecxfoi.wbl.wienerbergerfrontend.utils.SettingsManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import javax.inject.Inject;

public class SettingsFragment extends BaseFragment<SettingsViewModel>
{
    @Inject
    ViewModelProvider.Factory factory;

    private SettingsViewModel viewModel;
    private SettingsFragmentBinding binding;
    private HashMap<Integer, String> loginMethodStringsMap;
    private String string_classic_login;
    private String string_pin_login;
    private String string_fingerprint_login;

    @Override
    public SettingsViewModel getViewModel()
    {
        viewModel = new ViewModelProvider(this, factory).get(SettingsViewModel.class);
        return viewModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = SettingsFragmentBinding.inflate(inflater, container, false);

        string_classic_login = getString(R.string.classic_login);
        string_pin_login = getString(R.string.pin_login);
        string_fingerprint_login = getString(R.string.fingerprint_login);

        loginMethodStringsMap = new HashMap<Integer, String>()
        {
            {
                put(0, string_classic_login);
                put(1, string_pin_login);
                put(2, string_fingerprint_login);
            }
        };

        initNavigation();
        initSpinner();

        binding.setSettingsViewModel(viewModel);

        return binding.getRoot();
    }

    private void initSpinner()
    {
        final Spinner spinner = binding.loginMethodSpinner;

        ArrayList<String> options = new ArrayList<>(loginMethodStringsMap.values());

        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, options)
        {
            @Override
            public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent)
            {
                return super.getDropDownView(position, convertView, parent);
            }
        };

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            boolean firstTime = true;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (firstTime)
                {
                    firstTime = false;
                }
                else
                {
                    setNewMethod(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });
    }

    private void initNavigation()
    {
        SettingsManager.LoginMethods currentMethod = SettingsManager.getRememberLogin(getContext());

        if (currentMethod == SettingsManager.LoginMethods.NONE)
        {
            viewModel.setRememberLogin(false);
        }
        else
        {
            viewModel.setRememberLogin(true);

            switch (currentMethod)
            {
                case CLASSIC:
                {
                    viewModel.selectedItemIndex.set(0);
                    break;
                }
                case PIN:
                {
                    viewModel.selectedItemIndex.set(1);
                    break;
                }
                case FINGERPRINT:
                {
                    viewModel.selectedItemIndex.set(2);
                    break;
                }
            }
        }
    }

    private void setNewMethod(final int itemIndex)
    {
        SettingsManager.LoginMethods newLoginMethod;
        String loginMethodString = loginMethodStringsMap.get(itemIndex);

        if (Objects.equals(loginMethodString, string_classic_login))
        {
            newLoginMethod = CLASSIC;
        }
        else if (Objects.equals(loginMethodString, string_pin_login))
        {
            newLoginMethod = PIN;
        }
        else if (Objects.equals(loginMethodString, string_fingerprint_login))
        {
            newLoginMethod = FINGERPRINT;
        }
        else
        {
            newLoginMethod = NONE;
        }

        SettingsManager.setRememberLogin(newLoginMethod, getContext());
    }
}