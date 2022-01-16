package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.materialbalance;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ecxfoi.wbl.wienerbergerfrontend.api.SelectedCompanyData;
import com.ecxfoi.wbl.wienerbergerfrontend.models.MaterialBalanceData;
import com.ecxfoi.wbl.wienerbergerfrontend.models.OrderData;
import com.ecxfoi.wbl.wienerbergerfrontend.repositories.MaterialBalanceRepository;
import com.ecxfoi.wbl.wienerbergerfrontend.repositories.OrdersRepository;

import java.util.ArrayList;

import javax.inject.Inject;

public class MaterialBalanceViewModel extends ViewModel
{
    private final MaterialBalanceRepository materialBalanceRepository;
    private final SelectedCompanyData selectedCompanyData;
    private final ObservableField<String> errorMessage;

    @Inject
    public MaterialBalanceViewModel(final MaterialBalanceRepository materialBalanceRepository, SelectedCompanyData selectedCompanyData)
    {
        this.materialBalanceRepository = materialBalanceRepository;
        this.selectedCompanyData = selectedCompanyData;
        errorMessage = new ObservableField<>();
    }

    public LiveData<ArrayList<MaterialBalanceData>> fetchMaterialBalanceData()
    {
        Long selectedCompanyId = selectedCompanyData.getCompanyId();
        return materialBalanceRepository.getMaterialBalance(selectedCompanyId);
    }

    public ObservableField<String> getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(final String error)
    {
        errorMessage.set(error);
    }
}