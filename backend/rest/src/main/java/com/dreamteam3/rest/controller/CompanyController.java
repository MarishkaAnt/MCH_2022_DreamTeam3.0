package com.dreamteam3.rest.controller;

import com.dreamteam3.data.model.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/company")
@RequiredArgsConstructor
public class CompanyController {
    //TODO набросок контроллера, нужно продумать все методы и реализовать
    private final CompanyService companyService;

    @GetMapping
    public List<Company> getAll() {
        return companyService.findAll();
    }

}
