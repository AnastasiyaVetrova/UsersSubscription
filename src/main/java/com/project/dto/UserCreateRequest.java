package com.project.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserCreateRequest(
        @NotBlank(message = "Имя не может быть пустым")
        String username,

        @NotBlank(message = "Укажите email")
        @Email(message = "Неверный формат email")
        String email) {
}


