package io.nerdbyteslns.userservice.dtos.responses;

import java.time.LocalDateTime;
import java.util.List;

public record AppUserFullResponseDto(String id, String firstName, String lastName, String email, String phone, boolean isActive, boolean isDeleted, boolean isEmailVerified, boolean isPhoneVerified, List<UserRoleResponseDto> roles, LocalDateTime createdAt) {
}
