package org.example;

import jakarta.validation.constraints.NotBlank;

public record PostRequestRecord(
        @NotBlank(message = "Login cannot be empty")
        String login,

        @NotBlank(message = "Password cannot be empty")
        String password) {
}
