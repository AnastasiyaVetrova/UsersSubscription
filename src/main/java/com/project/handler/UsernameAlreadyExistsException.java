package com.project.handler;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException() {
        super("Пользователь с таким именем уже существует");
    }
}
