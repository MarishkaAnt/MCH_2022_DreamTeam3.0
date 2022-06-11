package com.dreamteam3.data.repositories;

import com.dreamteam3.data.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByName(String name);

    Optional<Role> findOneByDefaultRole(boolean isDefault);

    Optional<Role> findById(Long id);

    Page<Role> findAll(Pageable pageable);

    Set<Role> findAll();
}
