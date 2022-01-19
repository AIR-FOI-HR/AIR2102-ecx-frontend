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

    private boolean isOrderNrEntered()
    {
        return !orderNumber.get().isEmpty();
    }

    private boolean isDateEntered()
    {
        return !dateFrom.get().isEmpty() || !dateTo.get().isEmpty();
    }

    private boolean isNoteNrEntered()
    {
        return !deliveryNoteNumber.get().isEmpty();
    }

    public LiveData<ArrayList<DeliveryNoteData>> getRequestedDeliveryNotes()
    {
        ArrayList<DeliveryNoteData> emptyDeliveryNoteList = new ArrayList<>();
        MutableLiveData<ArrayList<DeliveryNoteData>> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(emptyDeliveryNoteList);

        if (isOrderNrEntered() && isNoteNrEntered() || isOrderNrEntered() && isDateEntered() || isDateEntered() && isNoteNrEntered())
        {
            errorMessage.set("Please select only one option \n (date/order number/delivery note number)");
        }
        else
        {
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
            else
            {
                errorMessage.set("Please select one of the options");
            }
        }

        return mutableLiveData;
    }
}
