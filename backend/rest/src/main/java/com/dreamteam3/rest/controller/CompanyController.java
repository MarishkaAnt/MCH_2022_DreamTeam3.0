package com.dreamteam3.rest.controller;

import com.dreamteam3.data.dto.CompanyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/company")
@RequiredArgsConstructor
public class CompanyController {
    //TODO набросок контроллера, ждем сервисы
    //private final CompanyService companyService;
    CompanyDto companyDto = CompanyDto.builder()
            .id(1L)
            .name("Test")
            .build();

    @GetMapping
    public List<CompanyDto> findAll() {
        return Arrays.asList(companyDto);
        //return companyService.findAll();
    }

}
