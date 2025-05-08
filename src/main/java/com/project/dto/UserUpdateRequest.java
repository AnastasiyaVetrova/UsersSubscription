package com.project.dto;

import jakarta.validation.constraints.Email;

public record UserUpdateRequest(
        String username,

        @Email
        String email) {
}
