package com.ecxfoi.wbl.wienerbergerfrontend.ui.companyselection;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ecxfoi.wbl.wienerbergerfrontend.api.SelectedCompanyData;
import com.ecxfoi.wbl.wienerbergerfrontend.models.CompanyData;
import com.ecxfoi.wbl.wienerbergerfrontend.repositories.CompanyRepository;

import java.util.ArrayList;

import javax.inject.Inject;

public class CompanySelectionViewModel extends ViewModel
{
    private final CompanyRepository companyRepository;
    private SelectedCompanyData selectedCompanyData;

    @Inject
    public CompanySelectionViewModel(CompanyRepository companyRepository, SelectedCompanyData selectedCompanyData)
    {
        this.companyRepository = companyRepository;
        this.selectedCompanyData = selectedCompanyData;
    }

    public LiveData<ArrayList<CompanyData>> getCompanyData()
    {
        return companyRepository.getAllCompanies();
    }

    public void setSelectedCompanyId(Long selectedCompanyId)
    {
        selectedCompanyData.setCompanyId(selectedCompanyId);
    }
}