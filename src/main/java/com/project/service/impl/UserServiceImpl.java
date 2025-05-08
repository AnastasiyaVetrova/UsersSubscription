package com.project.service.impl;

import com.project.dto.UserCreateRequest;
import com.project.dto.UserUpdateRequest;
import com.project.handler.UserNotFoundException;
import com.project.handler.UsernameAlreadyExistsException;
import com.project.model.User;
import com.project.model.mapper.UserMapper;
import com.project.repository.UserRepository;
import com.project.dto.UserResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.project.service.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public UserResponse createUser(UserCreateRequest dto) {
        Optional<User> existUser = userRepository.findByUsername(dto.username());
        User user = existUser.orElseGet(() -> userRepository.save(userMapper.toEntity(dto)));
        return userMapper.toDto(user);
    }

    @Override
    public UserResponse getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public UserResponse updateUser(Integer id, UserUpdateRequest dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        if (dto.username() != null
                && userRepository.existsByUsername(dto.username())) {
            throw new UsernameAlreadyExistsException();
        }

        userMapper.updateUser(dto, user);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }
}
