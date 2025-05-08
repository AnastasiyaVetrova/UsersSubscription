package com.project.model.mapper;

import com.project.dto.UserUpdateRequest;
import com.project.model.User;
import com.project.dto.UserCreateRequest;
import com.project.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    User toEntity(UserCreateRequest dto);

    UserResponse toDto(User user);

    void updateUser(UserUpdateRequest dto, @MappingTarget User user);
}
