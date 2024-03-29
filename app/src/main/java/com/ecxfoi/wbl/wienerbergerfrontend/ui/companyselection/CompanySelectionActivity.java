package com.ecxfoi.wbl.wienerbergerfrontend.ui.companyselection;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.ecxfoi.wbl.wienerbergerfrontend.R;
import com.ecxfoi.wbl.wienerbergerfrontend.base.BaseActivity;
import com.ecxfoi.wbl.wienerbergerfrontend.databinding.ActivityCompanySelectionBinding;
import com.ecxfoi.wbl.wienerbergerfrontend.models.CompanyData;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.MainActivity;

import java.util.ArrayList;

import javax.inject.Inject;

public class CompanySelectionActivity extends BaseActivity<CompanySelectionViewModel>
{
    CompanySelectionViewModel viewModel;
    @Inject
    ViewModelProvider.Factory factory;
    private ActivityCompanySelectionBinding binding;
    private TextView tvAddressBar;
    private TextView tvStreet;
    private TextView tvCountry;
    private TextView tvCity;
    private TextView tvPostal;
    private Button btnContinue;
    private ArrayList<CompanyData> companies;

    @Override
    public CompanySelectionViewModel getViewModel()
    {
        viewModel = new ViewModelProvider(this, factory).get(CompanySelectionViewModel.class);
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        getBindings();

        viewModel.getCompanyData().observe(this, this::initDropdown);

        companies = new ArrayList<>();

        btnContinue.setOnClickListener(v -> continueToMainMenu());
    }

    private void getBindings()
    {
        binding = ActivityCompanySelectionBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        btnContinue = binding.btnNext;

        tvAddressBar = binding.tvAddressBar;
        tvStreet = binding.tvAddressStreet;
        tvPostal = binding.tvAddressPostal;
        tvCountry = binding.tvAddressCountry;
        tvCity = binding.tvAddressCity;
    }

    private void initDropdown(ArrayList<CompanyData> companyList)
    {
        final Spinner spinner = binding.spinnerCompanySelection;

        ArrayList<String> companyNames = new ArrayList<>();
        companyNames.add("Select a company...");

        companies = companyList;
        companyList.forEach(company -> companyNames.add(String.format("%s - %s", company.getId(), company.getName())));

        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.spinner_item, companyNames)
        {
            @Override
            public boolean isEnabled(int position)
            {
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent)
            {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;

                int textColor = position == 0 ? Color.GRAY : Color.BLACK;
                tv.setTextColor(textColor);

                return view;
            }
        };

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                //TODO: consider data binding instead of handling color changes here
                final Button nextButton = binding.btnNext;

                boolean isACompanySelected = position > 0;

                int buttonBackgroundId = isACompanySelected ? R.drawable.rounded_button_red : R.drawable.rounded_button_grey;
                int buttonColorId = isACompanySelected ? R.color.white : R.color.black;

                nextButton.setBackgroundResource(buttonBackgroundId);
                nextButton.setTextColor(ContextCompat.getColor(getApplicationContext(), buttonColorId));
                nextButton.setEnabled(isACompanySelected);

                updateSelectedCompanyData(position - 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });
    }

    private void updateSelectedCompanyData(int companyIdx)
    {
        if (companyIdx == -1)
        {
            return;
        }

        try
        {
            CompanyData selectedCompany = companies.get(companyIdx);

            tvCountry.setText(selectedCompany.getAddressCountryCode());
            tvCity.setText(selectedCompany.getAddressCity());
            tvPostal.setText(selectedCompany.getAddressPostCode());
            tvStreet.setText(selectedCompany.getAddressStreet());
            tvAddressBar.setVisibility(View.VISIBLE);

            viewModel.setSelectedCompanyId((long) selectedCompany.getId());
        }
        catch (IndexOutOfBoundsException ignored)
        {
        }
    }

    private void continueToMainMenu()
    {
        finishAndRemoveTask();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}