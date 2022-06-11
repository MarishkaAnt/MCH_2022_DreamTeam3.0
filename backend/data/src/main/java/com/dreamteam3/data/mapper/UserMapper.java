package com.dreamteam3.data.mapper;

import com.dreamteam3.data.dto.UserDto;
import com.dreamteam3.data.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {
}
