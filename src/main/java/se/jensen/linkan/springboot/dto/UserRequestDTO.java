package se.jensen.linkan.springboot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(

        @NotBlank(message = "Användarnamnet får inte vara tomt.")
        @Size(min = 3, max = 50, message = "Användarnamnet måste vara mellan 3 och 50 tecken")
        String username,

        @NotBlank(message = "E-post får inte vara tomt.")
        @Email(message = "E-post måste vara en giltig adress.")
        String email,

        @NotBlank(message = "Password får inte vara tomt")
        @Size(min = 6, max = 100, message = "Password måste vara minst 6 tecken")
        String password,

        @NotBlank(message = "Roll får inte vara tomt")
        String role,

        @NotBlank(message = "Visningsnamn får inte vara tomt.")
        String displayName,

        @NotBlank(message = "Bio får inte vara tomt.")
        String bio,

        String profileImagePath
) {
}
