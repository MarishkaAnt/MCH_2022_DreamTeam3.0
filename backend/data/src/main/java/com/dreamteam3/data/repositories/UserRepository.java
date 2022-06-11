package com.dreamteam3.data.repositories;

import com.dreamteam3.data.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    Optional<User> findById(Long id);

    Optional<User> findByEmailIgnoreCase(String email);

    Optional<User> findByFirstNameAndLastNameAndPatronymic(String firstName, String lastName, String patronymic);

}
