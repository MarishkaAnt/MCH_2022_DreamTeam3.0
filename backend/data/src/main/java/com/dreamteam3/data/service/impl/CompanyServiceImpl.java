package com.dreamteam3.data.service.impl;

import com.dreamteam3.data.model.Company;
import com.dreamteam3.data.repository.CompanyRepository;
import com.dreamteam3.data.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }
}
