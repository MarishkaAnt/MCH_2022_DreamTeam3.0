package com.dreamteam3.company.uploader;

import com.dreamteam3.company.uploader.enums.ResourceType;
import com.dreamteam3.company.uploader.factory.ComplanyUploaderFactory;
import com.dreamteam3.company.uploader.service.CompanyUploader;
import com.dreamteam3.data.model.DataResource;
import com.dreamteam3.data.service.DataResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@SpringBootApplication(scanBasePackages = {
        "com.dreamteam3.data",
        "com.dreamteam3.company.uploader"
})
@EnableJpaRepositories(basePackages = {"com.dreamteam3.data.repository"})
@EntityScan(basePackages = {"com.dreamteam3.data.model"})
@RequiredArgsConstructor
public class CompanyUploaderUtility implements CommandLineRunner {

    @Value("${com.dreamteam3.mosprom.company-uploader.resourceType}")
    private ResourceType resourceType;

    @Value("${com.dreamteam3.mosprom.company-uploader.filePath}")
    private String filePath;

    private final ComplanyUploaderFactory complanyUploaderFactory;
    private final DataResourceService dataResourceService;

    public static void main(String[] args) {
        SpringApplication.run(CompanyUploaderUtility.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        switch (resourceType) {
            case FILE:
                CompanyUploader fileUploader = complanyUploaderFactory.getCompanyUploader();
                fileUploader.setResourceType(ResourceType.FILE);
                fileUploader.setResource(filePath);
                fileUploader.upload();
                break;
            case DATABASE:
                List<DataResource> resources = dataResourceService.findAll();
                CompanyUploader dbUploader = complanyUploaderFactory.getCompanyUploader();
                dbUploader.setResourceType(ResourceType.DATABASE);
                dbUploader.setResource(resources.get(0).getUrl());
                dbUploader.upload();
                break;
        }

    }
}
