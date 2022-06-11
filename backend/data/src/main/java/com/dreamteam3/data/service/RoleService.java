package com.dreamteam3.data.service;

import com.dreamteam3.data.model.Role;
import com.dreamteam3.data.repository.RoleRepository;
import com.dreamteam3.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public void createRole(Role role) {
        Role newRole = new Role();

        newRole.setName(role.getName());

        roleRepository.save(newRole);
    }

    public void deleteRole(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(NullPointerException::new);

        roleRepository.delete(role);
    }
}
