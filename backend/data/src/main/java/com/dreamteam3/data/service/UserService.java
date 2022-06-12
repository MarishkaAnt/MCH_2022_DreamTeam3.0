package com.dreamteam3.data.service;

import com.dreamteam3.data.model.Role;
import com.dreamteam3.data.model.User;
import com.dreamteam3.data.repository.RoleRepository;
import com.dreamteam3.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User save(User user) {
        user.setRole(roleRepository.findByName("USER"));
        return userRepository.saveAndFlush(user);
    }

    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(NullPointerException::new);
        userRepository.deleteById(id);
    }

    public void setUserRole(Long userId, Long roleId){
        Role role = roleRepository.findById(roleId).orElseThrow(NullPointerException::new);

        User user = userRepository.findById(userId).orElseThrow(NullPointerException::new);

        user.setRole(role);
        userRepository.save(user);
    }

    /*public void updateActivity(Long id, boolean enable) {
        User user = userRepository.findById(id).orElseThrow(NullPointerException::new);
        if (user.isActive() != enable) {
            user.setActive(enable);
            userRepository.save(user);
        }
    }*/
}
