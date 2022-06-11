package com.dreamteam3.rest.controller;

import com.dreamteam3.data.dto.CompanyDto;
import com.dreamteam3.data.mapper.CompanyMapper;
import com.dreamteam3.data.model.Company;
import com.dreamteam3.data.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    @GetMapping
    public List<CompanyDto> findAll() {
        return companyService.findAll().stream()
                .map(companyMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public CompanyDto create(@RequestBody CompanyDto companyDto) {
        Company company = companyService.save(companyMapper.toEntity(companyDto));
        return companyMapper.toDTO(company);
    }

    @PutMapping
    public CompanyDto update(@RequestBody CompanyDto companyDto) {
        Company company = companyService.save(companyMapper.toEntity(companyDto));
        return companyMapper.toDTO(company);
    }

}
