package com.ecxfoi.wbl.wienerbergerfrontend.ui.companyselection;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ecxfoi.wbl.wienerbergerfrontend.models.CompanyData;
import com.ecxfoi.wbl.wienerbergerfrontend.repositories.CompanyRepository;

import java.util.ArrayList;

import javax.inject.Inject;

public class CompanySelectionViewModel extends ViewModel
{
    CompanyRepository companyRepository;

    @Inject
    public CompanySelectionViewModel(CompanyRepository companyRepository)
    {
        this.companyRepository = companyRepository;
    }

    public LiveData<ArrayList<CompanyData>> getCompanyData() {
        return companyRepository.getAllCompanies();
    }
}