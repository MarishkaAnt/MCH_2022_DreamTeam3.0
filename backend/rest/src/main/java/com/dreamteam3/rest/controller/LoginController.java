package com.dreamteam3.rest.controller;

import com.dreamteam3.data.dto.UserDto;
import com.dreamteam3.data.mapper.UserMapper;
import com.dreamteam3.data.model.User;
import com.dreamteam3.data.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/login")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public Optional<UserDto> login(@RequestBody UserDto userDto) {
        Optional<User> user = userService.findByEmail(userDto.getEmail());
        return user.map(userMapper::toDTO);
    }

}
