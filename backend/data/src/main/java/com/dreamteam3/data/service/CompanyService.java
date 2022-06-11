package com.dreamteam3.data.service;

import com.dreamteam3.data.model.Company;
import com.dreamteam3.data.repository.CompanyRepository;
import com.dreamteam3.data.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ProductRepository productRepository;


    public void createCompany(Company company) {
        Company newCompany = new Company();

        newCompany.setName(company.getName());
        newCompany.setInn(company.getInn());
        newCompany.setOkved(company.getOkved());
        newCompany.setAddress(company.getAddress());
        newCompany.setProduct(company.getProduct());

        companyRepository.save(company);
    }

    public void deleteCompany(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(NullPointerException::new);
        companyRepository.deleteById(id);
    }

    public List<Company> findAll() {
        return new ArrayList<Company>(companyRepository.findAll());
    }
}
