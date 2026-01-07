package se.jensen.linkan.springboot.dto;

import java.time.LocalDateTime;

public record PostResponseDTO(Long id, String text, LocalDateTime createdAt) {
}
