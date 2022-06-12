package com.dreamteam3.rest.controller;

import com.dreamteam3.data.dto.UserDto;
import com.dreamteam3.data.mapper.UserMapper;
import com.dreamteam3.data.model.User;
import com.dreamteam3.data.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> findAll() {
        List<User> users = userService.findAll();
        return users.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto userDTO) {
        User user = userService.save(userMapper.toEntity(userDTO));
        return userMapper.toDTO(user);
    }

    @PutMapping
    public UserDto update(@RequestBody UserDto userDTO) {
        User user = userService.save(userMapper.toEntity(userDTO));
        return userMapper.toDTO(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

}
