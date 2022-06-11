package com.dreamteam3.company.uploader.factory;

import com.dreamteam3.company.uploader.service.CompanyUploader;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public abstract class ComplanyUploaderFactory {

    @Lookup
    public abstract CompanyUploader getCompanyUploader();

}
