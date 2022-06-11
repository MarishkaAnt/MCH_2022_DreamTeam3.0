package com.dreamteam3.data.services;

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

    public User buildUser(User user) {
        User newUser = new User();

        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPatronymic(user.getPatronymic());
        newUser.setActive(false);
        newUser.setRole(user.getRole());
        newUser.setCompany(user.getCompany());

        return newUser;
    }

    public void createUser(User user) {
        userRepository.save(buildUser(user));
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(NullPointerException::new);
        userRepository.deleteById(id);
    }

    public void updateActivity(Long id, boolean enadble) {
        User user = userRepository.findById(id).orElseThrow(NullPointerException::new);
        if (user.isActive() != enadble) {
            user.setActive(enadble);
            userRepository.save(user);
        }
    }
}
