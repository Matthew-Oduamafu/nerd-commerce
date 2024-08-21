package io.nerdbyteslns.userservice.dtos.responses;

import java.time.LocalDateTime;

public record UserRoleResponseDto(String id, String name, String description, boolean isActive, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
