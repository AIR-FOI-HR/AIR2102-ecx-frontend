package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.orders;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ecxfoi.wbl.wienerbergerfrontend.models.OrderDetailsData;
import com.ecxfoi.wbl.wienerbergerfrontend.repositories.OrdersRepository;

import javax.inject.Inject;

public class OrderDetailsViewModel extends ViewModel
{
    private final OrdersRepository ordersRepository;
    private final ObservableField<OrderDetailsData> orderData;
    public final ObservableField<String> errorMessage;

    @Inject
    public OrderDetailsViewModel(final OrdersRepository ordersRepository)
    {
        this.ordersRepository = ordersRepository;
        orderData = new ObservableField<>();
        errorMessage = new ObservableField<>();
    }

    public ObservableField<OrderDetailsData> getTicketData()
    {
        return orderData;
    }

    public LiveData<OrderDetailsData> fetchOrderData(Long id)
    {
        errorMessage.set("");
        return ordersRepository.getOrderData(id);
    }

    public ObservableField<OrderDetailsData> getOrderData()
    {
        return orderData;
    }

    public void setOrderData(final OrderDetailsData orderData)
    {
        this.orderData.set(orderData);
    }
}
