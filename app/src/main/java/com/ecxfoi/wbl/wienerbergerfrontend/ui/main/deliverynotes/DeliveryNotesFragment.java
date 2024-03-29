package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.deliverynotes;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ecxfoi.wbl.wienerbergerfrontend.R;
import com.ecxfoi.wbl.wienerbergerfrontend.base.BaseFragment;
import com.ecxfoi.wbl.wienerbergerfrontend.databinding.FragmentDeliveryNotesBinding;
import com.ecxfoi.wbl.wienerbergerfrontend.models.DeliveryNoteData;

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

        initializeDatePickers();

        binding.btnRequest.setOnClickListener(v -> viewModel.getRequestedDeliveryNotes().observe(getViewLifecycleOwner(), this::setRequestedData));

        return binding.getRoot();
    }

    private void initializeDatePickers()
    {
        new EditTextDatePicker(getActivity(), binding.txtDeliveriesFrom);
        new EditTextDatePicker(getActivity(), binding.txtDeliveriesTo);
    }

    private void setRequestedData(List<DeliveryNoteData> deliveryNoteData)
    {
        binding.listViewOrderNotes.setLayoutManager(new LinearLayoutManager(getActivity()));
        DeliveryNotesRecyclerAdapter recyclerAdapter = new DeliveryNotesRecyclerAdapter(new ArrayList<>(deliveryNoteData))
        {
        };
        binding.listViewOrderNotes.setAdapter(recyclerAdapter);

        if (!deliveryNoteData.isEmpty())
        {
            binding.scrollView.fullScroll(View.FOCUS_DOWN);
        }
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

        dialog.setInverseBackgroundForced(true);

        dialog.setButton(DialogInterface.BUTTON_NEUTRAL, v.getContext().getString(R.string.reset), (dialog1, which) -> editText.setText(""));

        dialog.show();
    }

    private void updateDisplay()
    {
        editText.setText(new StringBuilder().append(birthYear).append("-").append(month + 1).append("-").append(day).append(" "));
    }
}