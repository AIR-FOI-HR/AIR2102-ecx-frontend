package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.deliverynotes;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ecxfoi.wbl.wienerbergerfrontend.models.DeliveryNoteData;
import com.ecxfoi.wbl.wienerbergerfrontend.repositories.DeliveryNotesRepository;

import java.util.ArrayList;
import java.util.Objects;

public class DeliveryNotesViewModel extends ViewModel
{
    private final DeliveryNotesRepository deliveryNotesRepository;
    public final ObservableField<String> dateFrom;
    public final ObservableField<String> dateTo;
    public final ObservableField<String> deliveryNoteNumber;
    public final ObservableField<String> orderNumber;
    public final ObservableField<String> errorMessage;

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
        return !Objects.requireNonNull(orderNumber.get()).isEmpty();
    }

    private boolean isDateEntered()
    {
        return !Objects.requireNonNull(dateFrom.get()).isEmpty() || !Objects.requireNonNull(dateTo.get()).isEmpty();
    }

    private boolean isNoteNrEntered()
    {
        return !Objects.requireNonNull(deliveryNoteNumber.get()).isEmpty();
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
            if (!Objects.requireNonNull(dateFrom.get()).isEmpty() && !Objects.requireNonNull(dateTo.get()).isEmpty())
            {
                return deliveryNotesRepository.getDeliveryNotesForDateRange(dateFrom.get(), dateTo.get());
            }
            else if (!Objects.requireNonNull(orderNumber.get()).isEmpty())
            {
                return deliveryNotesRepository.getDeliveryNotesForOrder(Long.parseLong(Objects.requireNonNull(orderNumber.get())));
            }
            else if (!Objects.requireNonNull(deliveryNoteNumber.get()).isEmpty())
            {
                return deliveryNotesRepository.getDeliveryNoteByDeliveryNoteId(Long.parseLong(Objects.requireNonNull(deliveryNoteNumber.get())));
            }
            else
            {
                errorMessage.set("Please select one of the options");
            }
        }

        return mutableLiveData;
    }
}
