package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.orders;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ecxfoi.wbl.wienerbergerfrontend.api.SelectedCompanyData;
import com.ecxfoi.wbl.wienerbergerfrontend.models.OrderData;
import com.ecxfoi.wbl.wienerbergerfrontend.repositories.OrdersRepository;

import java.util.ArrayList;

import javax.inject.Inject;

public class OrdersViewModel extends ViewModel
{
    private final OrdersRepository ordersRepository;
    private final SelectedCompanyData selectedCompanyData;
    private final ObservableField<ArrayList<OrderData>> ordersData;

    @Inject
    public OrdersViewModel(final OrdersRepository ordersRepository, SelectedCompanyData selectedCompanyData)
    {
        this.ordersRepository = ordersRepository;
        this.selectedCompanyData = selectedCompanyData;
        ordersData = new ObservableField<>();
    }

    public LiveData<ArrayList<OrderData>> fetchOrderData()
    {
        Long selectedCompanyId = selectedCompanyData.getCompanyId();
        return ordersRepository.getAllOrders(selectedCompanyId);
    }
}
