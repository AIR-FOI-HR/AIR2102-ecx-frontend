package com.ecxfoi.wbl.wienerbergerfrontend.api;

import javax.inject.Inject;

public class SelectedCompanyData
{
    private Long companyId;

    @Inject
    public SelectedCompanyData()
    {

    }

    public Long getCompanyId()
    {
        return companyId;
    }

    public void setCompanyId(final Long companyId)
    {
        this.companyId = companyId;
    }
}
