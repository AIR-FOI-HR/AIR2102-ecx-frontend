package com.ecxfoi.wbl.wienerbergerfrontend;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompanySelectionActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //TODO: replace title bar removal with a general style (instead of repeating the following 10 lines in every activity)
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        if (getSupportActionBar() != null)
        {
            getSupportActionBar().hide();
        }

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_company_selection);
        initDropdown();
    }

    private void initDropdown()
    {
        final Spinner spinner = (Spinner) findViewById(R.id.spinnerCompanySelection);

        String[] companiesMock = new String[]{
                "Select a company...",
                "McCarthy",
                "Strabag"
        };

        final List<String> companiesList = new ArrayList<>(Arrays.asList(companiesMock));

        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.spinner_item, companiesList)
        {
            @Override
            public boolean isEnabled(int position)
            {
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent)
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
                final Button nextButton = (Button) findViewById(R.id.btnNext);

                boolean isACompanySelected = position > 0;

                int buttonId = isACompanySelected ? R.drawable.rounded_button_red : R.drawable.rounded_button_grey;
                nextButton.setBackgroundResource(buttonId);
                nextButton.setEnabled(isACompanySelected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });
    }
}