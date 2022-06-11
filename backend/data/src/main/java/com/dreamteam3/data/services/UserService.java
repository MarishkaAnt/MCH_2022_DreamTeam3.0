package com.dreamteam3.data.services;

import com.dreamteam3.data.model.Role;
import com.dreamteam3.data.model.User;
import com.dreamteam3.data.repositories.RoleRepository;
import com.dreamteam3.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    public void createUser(User user) {
        User newUser = new User();

        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPatronymic(user.getPatronymic());
        newUser.setActive(false);
        newUser.setRole(user.getRole());
        newUser.setCompany(user.getCompany());

        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(NullPointerException::new);
        userRepository.deleteById(id);
    }

    public void setUserRole(Long userId, Long roleId){
        Role role = roleRepository.findById(roleId).orElseThrow(NullPointerException::new);

        User user = userRepository.findById(userId).orElseThrow(NullPointerException::new);

        user.setRole(role);
        userRepository.save(user);
    }

    public void updateActivity(Long id, boolean enable) {
        User user = userRepository.findById(id).orElseThrow(NullPointerException::new);
        if (user.isActive() != enable) {
            user.setActive(enable);
            userRepository.save(user);
        }
    }
}
