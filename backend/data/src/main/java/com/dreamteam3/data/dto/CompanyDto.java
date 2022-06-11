package com.dreamteam3.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    private Long id;
    private String name;
    private String inn;
    private String okved;
    private String address;
    private String url;

}
