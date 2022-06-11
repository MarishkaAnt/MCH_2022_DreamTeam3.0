package com.dreamteam3.rest.controller;

import com.dreamteam3.data.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    //TODO набросок контроллера, ждем сервисы
    //private final UserService userService;
    UserDto userDto = UserDto.builder()
            .id(1L)
            .email("a@mail.ru")
            .firstName("Test")
            .build();

    @GetMapping
    public List<UserDto> findAll() {
        return Arrays.asList(userDto);
        //return userService.findAll();
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto userDTO) {
        return userDTO;
        //return userService.save(userDTO);
    }

}
