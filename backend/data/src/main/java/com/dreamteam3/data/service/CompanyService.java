package com.dreamteam3.data.service;

import com.dreamteam3.data.model.Company;
import com.dreamteam3.data.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Company save(Company company) {
        return companyRepository.saveAndFlush(company);
    }

    public List<Company> saveAll(List<Company> companies) {
        return companyRepository.saveAllAndFlush(companies);
    }

    public void delete(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(NullPointerException::new);
        companyRepository.deleteById(id);
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public List<Company> findAllByInnIn(List<String> inns) {
        return companyRepository.findAllByInnIn(inns);
    }
}
