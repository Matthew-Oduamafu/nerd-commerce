package io.nerdbyteslns.userservice.services;

import io.nerdbyteslns.nerdcommercecore.models.reponses.ApiResponse;
import io.nerdbyteslns.userservice.dtos.requests.CreateAppUserDto;
import io.nerdbyteslns.userservice.dtos.responses.AppUserFullResponseDto;
import io.nerdbyteslns.userservice.dtos.responses.AppUserResponseDto;

public interface AppUserService {
    ApiResponse<AppUserResponseDto> createUser(CreateAppUserDto request);

    ApiResponse<AppUserResponseDto> getUserById(String id);

    ApiResponse<AppUserResponseDto> getUserByEmail(String email);

    ApiResponse<AppUserFullResponseDto> getUserFullDetailsByEmail(String email);
    ApiResponse<AppUserResponseDto> deleteUser(String id);

    // add role to a user
    ApiResponse<AppUserFullResponseDto> addRoleToUser(String userId, String roleId);
    // remove a role from a user
    ApiResponse<AppUserFullResponseDto> removeRoleFromUser(String userId, String roleId);

}
