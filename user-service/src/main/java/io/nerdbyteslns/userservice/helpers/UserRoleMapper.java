package io.nerdbyteslns.userservice.helpers;

import io.nerdbyteslns.userservice.domains.UserRole;
import io.nerdbyteslns.userservice.dtos.requests.CreateUserRoleDto;
import io.nerdbyteslns.userservice.dtos.responses.UserRoleResponseDto;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserRoleMapper {

    // map UserRole to UserRoleResponseDto note UserRoleResponseDto is a record not a class
    public static UserRoleResponseDto mapToUserRoleResponseDto(UserRole userRole) {
        return new UserRoleResponseDto(userRole.getId(), userRole.getName(), userRole.getDescription(), userRole.isActive(), userRole.getCreatedAt(), userRole.getUpdatedAt());
    }

    // map CreateUserRoleDto to UserRole
    public static UserRole mapToUserRole(CreateUserRoleDto createUserRoleDto) {
        return UserRole.builder()
                .id(UUID.randomUUID().toString())
                .name(createUserRoleDto.name())
                .description(createUserRoleDto.description())
                .isActive(true)
                .isDeleted(false)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
