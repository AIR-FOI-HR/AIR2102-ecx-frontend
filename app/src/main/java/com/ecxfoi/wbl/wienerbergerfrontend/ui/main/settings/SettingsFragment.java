package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.settings;

import static com.ecxfoi.wbl.wienerbergerfrontend.utils.SettingsManager.LoginMethods.CLASSIC;
import static com.ecxfoi.wbl.wienerbergerfrontend.utils.SettingsManager.LoginMethods.FINGERPRINT;
import static com.ecxfoi.wbl.wienerbergerfrontend.utils.SettingsManager.LoginMethods.PIN;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.ecxfoi.wbl.wienerbergerfrontend.R;
import com.ecxfoi.wbl.wienerbergerfrontend.auth.AuthService;
import com.ecxfoi.wbl.wienerbergerfrontend.base.BaseFragment;
import com.ecxfoi.wbl.wienerbergerfrontend.databinding.SettingsFragmentBinding;
import com.ecxfoi.wbl.wienerbergerfrontend.utils.SettingsManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

public class SettingsFragment extends BaseFragment<SettingsViewModel> {
    @Inject
    ViewModelProvider.Factory factory;

    private static class SpinnerEntry {
        public int index;
        public String description;
        public SettingsManager.LoginMethods method;

        public SpinnerEntry(final int index, final String description, final SettingsManager.LoginMethods method) {
            this.index = index;
            this.description = description;
            this.method = method;
        }
    }

    private SettingsViewModel viewModel;
    private SettingsFragmentBinding binding;
    private ArrayList<SpinnerEntry> loginMethodEntries;

    @Override
    public SettingsViewModel getViewModel() {
        viewModel = new ViewModelProvider(this, factory).get(SettingsViewModel.class);
        return viewModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = SettingsFragmentBinding.inflate(inflater, container, false);

        loginMethodEntries = new ArrayList<SpinnerEntry>() {
            {
                add(new SpinnerEntry(0, getString(R.string.classic_login), CLASSIC));
                add(new SpinnerEntry(1, getString(R.string.pin_login), PIN));
                add(new SpinnerEntry(2, getString(R.string.fingerprint_login), FINGERPRINT));
            }
        };

        checkFingerprintAvailability();
        initNavigation();
        initSpinner();

        binding.setSettingsViewModel(viewModel);

        return binding.getRoot();
    }

    private void checkFingerprintAvailability() {
        if (!viewModel.isFingerprintAvailable(getActivity())) {
            loginMethodEntries.removeIf(entry -> entry.description.contains("Fingerprint"));
        }
    }

    private void initSpinner() {
        final Spinner spinner = binding.loginMethodSpinner;

        List<String> options = loginMethodEntries.stream().map(spinnerEntry -> spinnerEntry.description).collect(Collectors.toList());

        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, options) {
            @Override
            public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
                return super.getDropDownView(position, convertView, parent);
            }
        };

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            boolean firstTime = true;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (firstTime) {
                    firstTime = false;
                } else {
                    setNewMethod(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void initNavigation() {
        binding.pinEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence charSequence, final int i, final int i1, final int i2) {
            }

            @Override
            public void afterTextChanged(final Editable editable) {
            }

            @Override
            public void onTextChanged(final CharSequence charSequence, final int start, final int before, final int count) {
                if (charSequence.length() == 4) {
                    AuthService.setPIN(charSequence.toString(), getContext());
                    viewModel.setDoRememberLogin(PIN, getContext());
                    if (count == 1) // If manual change happened, notify that this new PIN is actually remembered.
                    {
                        Toast.makeText(getContext(), "New PIN remembered!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        SettingsManager.LoginMethods currentMethod = SettingsManager.getRememberLogin(getContext());
        if (currentMethod == PIN) {
            Toast.makeText(getContext(), R.string.enter_new_pin_message, Toast.LENGTH_LONG).show();
        }
        viewModel.setDoRememberLogin(currentMethod, getContext());
    }

    private void setNewMethod(final int itemIndex) {
        SettingsManager.LoginMethods newMethod = loginMethodEntries.stream().filter(spinnerEntry -> spinnerEntry.index == itemIndex).findAny().orElse(loginMethodEntries.get(0)).method;
        viewModel.setDoRememberLogin(newMethod, getContext());
    }
}