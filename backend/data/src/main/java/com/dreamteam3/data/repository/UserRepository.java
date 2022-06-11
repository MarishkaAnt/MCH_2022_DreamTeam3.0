package com.dreamteam3.data.repository;

import com.dreamteam3.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailIgnoreCase(String email);

    Optional<User> findByFirstNameAndLastNameAndPatronymic(String firstName, String lastName, String patronymic);

}
