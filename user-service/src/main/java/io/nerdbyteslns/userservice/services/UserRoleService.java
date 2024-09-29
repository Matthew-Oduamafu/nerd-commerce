package io.nerdbyteslns.userservice.services;

import io.nerdbyteslns.nerdcommercecore.models.reponses.ApiResponse;
import io.nerdbyteslns.userservice.dtos.requests.CreateUserRoleDto;
import io.nerdbyteslns.userservice.dtos.requests.UpdateUserRoleDto;
import io.nerdbyteslns.userservice.dtos.responses.UserRoleResponseDto;

import java.util.List;

public interface UserRoleService {
    void seedRolesInDB();
    ApiResponse<UserRoleResponseDto> createRole(CreateUserRoleDto request);
    ApiResponse<UserRoleResponseDto> getRoleById(String id);
    ApiResponse<List<UserRoleResponseDto>> getRoles();
    ApiResponse<UserRoleResponseDto> getRoleByName(String name);
    ApiResponse<UserRoleResponseDto> updateRole(String id, UpdateUserRoleDto request);
    ApiResponse<UserRoleResponseDto> deleteRole(String id);
}
