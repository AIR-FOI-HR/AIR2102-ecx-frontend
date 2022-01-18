package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.deliverynotes;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ecxfoi.wbl.wienerbergerfrontend.models.DeliveryNoteData;
import com.ecxfoi.wbl.wienerbergerfrontend.repositories.DeliveryNotesRepository;

import java.util.ArrayList;

public class DeliveryNotesViewModel extends ViewModel
{
    public ObservableField<String> dateFrom;
    public ObservableField<String> dateTo;
    public ObservableField<String> deliveryNoteNumber;
    public ObservableField<String> orderNumber;
    public ObservableField<String> errorMessage;

    private final DeliveryNotesRepository deliveryNotesRepository;

    public DeliveryNotesViewModel(DeliveryNotesRepository deliveryNotesRepository)
    {
        this.deliveryNotesRepository = deliveryNotesRepository;

        dateFrom = new ObservableField<>("");
        dateTo = new ObservableField<>("");
        deliveryNoteNumber = new ObservableField<>("");
        orderNumber = new ObservableField<>("");
        errorMessage = new ObservableField<>("");
    }

    public LiveData<ArrayList<DeliveryNoteData>> getRequestedDeliveryNotes()
    {
        ArrayList<DeliveryNoteData> emptyDeliveryNoteList = new ArrayList<>();
        MutableLiveData<ArrayList<DeliveryNoteData>> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(emptyDeliveryNoteList);

        if (!orderNumber.get().isEmpty() && !dateFrom.get().isEmpty() && !dateTo.get().isEmpty())
        {
            errorMessage.set("Please select only one option (date/order number/delivery note number)");
        }

        errorMessage.set("");
        if (!dateFrom.get().isEmpty() && !dateTo.get().isEmpty())
        {
            return deliveryNotesRepository.getDeliveryNotesForDateRange(dateFrom.get(), dateTo.get());
        }
        else if (!orderNumber.get().isEmpty())
        {
            return deliveryNotesRepository.getDeliveryNotesForOrder(Long.parseLong(orderNumber.get()));
        }
        else if (!deliveryNoteNumber.get().isEmpty())
        {
            return deliveryNotesRepository.getDeliveryNoteByDeliveryNoteId(Long.parseLong(deliveryNoteNumber.get()));
        }

        return mutableLiveData;
    }
}
