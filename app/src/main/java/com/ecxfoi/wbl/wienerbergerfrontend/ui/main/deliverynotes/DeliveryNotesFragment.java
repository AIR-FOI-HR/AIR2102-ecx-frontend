package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.deliverynotes;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ecxfoi.wbl.wienerbergerfrontend.base.BaseFragment;
import com.ecxfoi.wbl.wienerbergerfrontend.databinding.FragmentDeliveryNotesBinding;
import com.ecxfoi.wbl.wienerbergerfrontend.models.DeliveryNoteData;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.inject.Inject;

public class DeliveryNotesFragment extends BaseFragment<DeliveryNotesViewModel>
{
    @Inject
    ViewModelProvider.Factory factory;

    private DeliveryNotesViewModel viewModel;
    private FragmentDeliveryNotesBinding binding;

    private EditText txtDateFrom;
    private EditText txtDateTo;

    private EditTextDatePicker datePickerFrom;
    private EditTextDatePicker datePickerTo;

    private Button btnRequest;

    @Override
    public DeliveryNotesViewModel getViewModel()
    {
        viewModel = new ViewModelProvider(this, factory).get(DeliveryNotesViewModel.class);
        return viewModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentDeliveryNotesBinding.inflate(inflater, container, false);
        binding.setDeliveryNotesViewModel(viewModel);

        txtDateFrom = binding.txtDeliveriesFrom;
        txtDateTo = binding.txtDeliveriesTo;

        datePickerFrom = new EditTextDatePicker(getActivity(), txtDateFrom);
        datePickerTo = new EditTextDatePicker(getActivity(), txtDateTo);

        btnRequest = binding.btnRequest;

        btnRequest.setOnClickListener(v -> {
            viewModel.getRequestedDeliveryNotes().observe(getViewLifecycleOwner(), this::setRequestedData);
        });

        return binding.getRoot();
    }

    private void setRequestedData(List<DeliveryNoteData> deliveryNoteData)
    {
        viewModel.setRequestedDeliveryNotes(deliveryNoteData);

        binding.listViewOrderNotes.setLayoutManager(new LinearLayoutManager(getActivity()));
        DeliveryNotesRecyclerAdapter recyclerAdapter = new DeliveryNotesRecyclerAdapter(new ArrayList<>(deliveryNoteData))
        {

        };
        binding.listViewOrderNotes.setAdapter(recyclerAdapter);
    }
}

class EditTextDatePicker implements View.OnClickListener, DatePickerDialog.OnDateSetListener
{
    Context context;
    EditText editText;
    private int day;
    private int month;
    private int birthYear;

    public EditTextDatePicker(Context context, EditText editText)
    {
        this.context = context;
        this.editText = editText;
        this.editText.setOnClickListener(this);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
    {
        this.birthYear = year;
        this.month = monthOfYear;
        this.day = dayOfMonth;
        updateDisplay();
    }

    @Override
    public void onClick(View v)
    {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        DatePickerDialog dialog = new DatePickerDialog(context, this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    private void updateDisplay()
    {
        editText.setText(new StringBuilder().append(birthYear).append("-").append(month + 1).append("-").append(day).append(" "));
    }
}