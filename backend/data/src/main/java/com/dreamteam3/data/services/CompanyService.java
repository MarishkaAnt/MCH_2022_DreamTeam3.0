package com.dreamteam3.data.services;

import com.dreamteam3.data.model.Company;
import com.dreamteam3.data.repositories.CompanyRepository;
import com.dreamteam3.data.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ProductRepository productRepository;

    public Company buildCompany(Company company) {
        Company newCompany = new Company();

        newCompany.setName(company.getName());
        newCompany.setInn(company.getInn());
        newCompany.setOkved(company.getOkved());
        newCompany.setAddress(company.getAddress());
        newCompany.setProduct(company.getProduct());

        return newCompany;
    }

    public void createCompany(Company company) {
        companyRepository.save(buildCompany(company));
    }

    public void deleteCompany(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(NullPointerException::new);
        companyRepository.deleteById(id);
    }
}
