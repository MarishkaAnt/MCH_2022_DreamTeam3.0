package com.dreamteam3.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long role_id;
    private Long company_id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;
    private String password;
    private boolean isActive;
}
