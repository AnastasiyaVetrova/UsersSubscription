package com.project.handler;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Integer id) {
        super(String.format("Пользователь с id = %d не найден", id));
    }
}
