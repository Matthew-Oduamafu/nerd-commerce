package io.nerdbyteslns.userservice.helpers;

import io.nerdbyteslns.userservice.domains.AppUser;
import io.nerdbyteslns.userservice.dtos.requests.CreateAppUserDto;
import io.nerdbyteslns.userservice.dtos.responses.AppUserFullResponseDto;
import io.nerdbyteslns.userservice.dtos.responses.AppUserResponseDto;
import io.nerdbyteslns.userservice.dtos.responses.UserRoleResponseDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AppUserMapper {

    // map AppUser to AppUserResponseDto note AppUserResponseDto is a record not a class
    public static AppUserResponseDto mapToAppUserResponseDto(AppUser appUser) {
        return new AppUserResponseDto(appUser.getId(), appUser.getFirstName(), appUser.getLastName(), appUser.getEmail(), appUser.getPhone());
    }

    public static AppUserFullResponseDto mapToAppUserFullResponseDto(AppUser appUser) {
        List<UserRoleResponseDto> userRoles = appUser.getRoles().stream().map(UserRoleMapper::mapToUserRoleResponseDto).toList();
        return new AppUserFullResponseDto(appUser.getId(), appUser.getFirstName(), appUser.getLastName(), appUser.getEmail(), appUser.getPhone(), appUser.isActive(), appUser.isDeleted(), appUser.isEmailVerified(), appUser.isPhoneVerified(), userRoles, appUser.getCreatedAt());
    }

    // map createAppUserDto to AppUser
    public static AppUser mapToAppUser(CreateAppUserDto createAppUserDto) {
        return AppUser.builder()
                .id(UUID.randomUUID().toString())
                .firstName(createAppUserDto.firstName())
                .lastName(createAppUserDto.lastName())
                .email(createAppUserDto.email())
                .phone(createAppUserDto.phone())
                .password(createAppUserDto.password())
                .isActive(true)
                .isDeleted(false)
                .isEmailVerified(false)
                .isPhoneVerified(false)
                .roles(new ArrayList<>())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
