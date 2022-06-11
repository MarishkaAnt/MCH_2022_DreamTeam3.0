package com.dreamteam3.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    String firstName;
    String lastName;
    String patronymic;
    String email;
    String password;

}
