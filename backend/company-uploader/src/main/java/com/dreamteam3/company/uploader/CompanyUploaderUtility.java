package com.dreamteam3.company.uploader;

import com.dreamteam3.company.uploader.enums.ResourceType;
import com.dreamteam3.company.uploader.factory.ComplanyUploaderFactory;
import com.dreamteam3.company.uploader.service.CompanyUploader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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

    public static void main(String[] args) {
        SpringApplication.run(CompanyUploaderUtility.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        switch (resourceType) {
            case FILE:
                CompanyUploader uploader = complanyUploaderFactory.getCompanyUploader();
                uploader.setResourceType(ResourceType.FILE);
                uploader.setResource(filePath);
                uploader.upload();
                break;
            case DATABASE:
                break;
        }

    }
}
