package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.deliverynotes;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ecxfoi.wbl.wienerbergerfrontend.models.DeliveryNoteData;
import com.ecxfoi.wbl.wienerbergerfrontend.repositories.DeliveryNotesRepository;

import java.util.ArrayList;
import java.util.List;

public class DeliveryNotesViewModel extends ViewModel
{
    public ObservableField<String> dateFrom;
    public ObservableField<String> dateTo;
    public ObservableField<String> deliveryNoteNumber;
    public ObservableField<String> orderNumber;

    private final DeliveryNotesRepository deliveryNotesRepository;

    public DeliveryNotesViewModel(DeliveryNotesRepository deliveryNotesRepository)
    {
        this.deliveryNotesRepository = deliveryNotesRepository;

        dateFrom = new ObservableField<>();
        dateTo = new ObservableField<>();
        deliveryNoteNumber = new ObservableField<>();
        orderNumber = new ObservableField<>();
    }

    public LiveData<ArrayList<DeliveryNoteData>> getRequestedDeliveryNotes()
    {
        return deliveryNotesRepository.getDeliverNotesForDateRange(dateFrom.get(), dateTo.get());
    }

    public void setRequestedDeliveryNotes(List<DeliveryNoteData> deliveryNoteData)
    {
        int x = 0;
    }
}
