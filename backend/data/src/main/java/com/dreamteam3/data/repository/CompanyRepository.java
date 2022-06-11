package com.dreamteam3.data.repository;

import com.dreamteam3.data.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByName(String name);

    Optional<Company> findByInn(Long inn);

    Collection<Company> findAllByOkved(String okved);
}
