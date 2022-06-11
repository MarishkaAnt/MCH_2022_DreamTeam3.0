package com.dreamteam3.data.mapper;

import com.dreamteam3.data.dto.CompanyDto;
import com.dreamteam3.data.model.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper extends EntityMapper<CompanyDto, Company>{
}
