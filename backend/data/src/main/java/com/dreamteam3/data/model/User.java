package com.dreamteam3.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    public static final String PASSWORD_FORMAT = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @OneToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @NotNull(message = "First name must be filled in")
    private String firstName;
    @NotNull(message = "Last name must be filled in")
    private String lastName;
    private String patronymic;
    @Email
    private String email;
    @NotNull(message = "Password must be minimum 8 characters, and contain at least one uppercase letter, " +
            "one lowercase letter and one number")
    @Pattern(regexp = PASSWORD_FORMAT)
    private String password;

}
