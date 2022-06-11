package com.dreamteam3.data.service;

import com.dreamteam3.data.model.Role;
import com.dreamteam3.data.repository.RoleRepository;
import com.dreamteam3.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

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
