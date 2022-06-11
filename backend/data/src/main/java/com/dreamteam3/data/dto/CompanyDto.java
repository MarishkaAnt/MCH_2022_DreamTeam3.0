package com.dreamteam3.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CompanyDto {

    private Long id;
    private String name;
    private String inn;
    private String okved;
    private String address;

}
