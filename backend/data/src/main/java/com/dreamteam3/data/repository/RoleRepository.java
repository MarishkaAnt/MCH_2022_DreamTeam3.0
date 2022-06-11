package com.dreamteam3.data.repository;

import com.dreamteam3.data.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    Optional<Role> findOneByDefaultRole(boolean isDefault);
}
