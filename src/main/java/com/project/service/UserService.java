package com.project.service;

import com.project.dto.UserCreateRequest;
import com.project.dto.UserResponse;
import com.project.dto.UserUpdateRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserResponse createUser(UserCreateRequest user);

    UserResponse getUserById(Integer id);

    UserResponse updateUser(Integer id, UserUpdateRequest dto);

    void deleteUser(Integer id);
}
