package com.dreamteam3.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;
    private String password;

    private RoleDto role;
    private CompanyDto company;

}
