package se.jensen.linkan.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostRequestDTO(
        
        @NotBlank(message = "Text får inte vara tom.")
        @Size(min = 3, max = 200, message = "Text måste vara mellan 3 och 200 tecken.")
        String text
) {
}
