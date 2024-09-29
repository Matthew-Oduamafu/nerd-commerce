package io.nerdbyteslns.userservice.services.impl;

import io.nerdbyteslns.nerdcommercecore.models.reponses.ApiResponse;
import io.nerdbyteslns.nerdcommercecore.utils.ApiResponseUtils;
import io.nerdbyteslns.userservice.domains.UserRole;
import io.nerdbyteslns.userservice.dtos.requests.CreateUserRoleDto;
import io.nerdbyteslns.userservice.dtos.requests.UpdateUserRoleDto;
import io.nerdbyteslns.userservice.dtos.responses.UserRoleResponseDto;
import io.nerdbyteslns.userservice.helpers.UserRoleMapper;
import io.nerdbyteslns.userservice.repositories.UserRoleRepository;
import io.nerdbyteslns.userservice.services.UserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;

    @Override
    public void seedRolesInDB() {
        log.info("Seeding roles in DB");
    }

    @Override
    public ApiResponse<UserRoleResponseDto> createRole(CreateUserRoleDto request) {
        log.info("Creating role: {}", request);
        if (userRoleRepository.existsByName(request.name())) {
            log.warn("Role already exists: {}", request.name());
            return ApiResponseUtils.badRequestApiResponse("Role already exists");
        }

        UserRole userRole = UserRoleMapper.mapToUserRole(request);
        userRole = userRoleRepository.save(userRole);
        UserRoleResponseDto userRoleResponseDto = UserRoleMapper.mapToUserRoleResponseDto(userRole);
        log.info("Role created: {}", userRoleResponseDto);
        return ApiResponseUtils.okApiResponse(userRoleResponseDto);
    }

    @Override
    public ApiResponse<UserRoleResponseDto> getRoleById(String id) {
        log.info("Getting role by id: {}", id);
        UserRole userRole = userRoleRepository.findById(id).orElse(null);
        if (userRole == null) {
            log.warn("Role not found: {}", id);
            return ApiResponseUtils.notFoundApiResponse();
        }
        UserRoleResponseDto userRoleResponseDto = UserRoleMapper.mapToUserRoleResponseDto(userRole);
        log.info("Role found: {}", userRoleResponseDto);
        return ApiResponseUtils.okApiResponse(userRoleResponseDto);
    }

    @Override
    public ApiResponse<List<UserRoleResponseDto>> getRoles() {
        log.info("Getting all roles");
        List<UserRole> userRoles = userRoleRepository.findAll();
        List<UserRoleResponseDto> userRoleResponseDtos = userRoles.stream().map(UserRoleMapper::mapToUserRoleResponseDto).toList();
        log.info("Roles found: {}", userRoleResponseDtos);
        return ApiResponseUtils.okApiResponse(userRoleResponseDtos);
    }

    @Override
    public ApiResponse<UserRoleResponseDto> getRoleByName(String name) {
        log.info("Getting role by name: {}", name);
        UserRole userRole = userRoleRepository.findByName(name);
        if (userRole == null) {
            log.warn("Role not found: {}", name);
            return ApiResponseUtils.notFoundApiResponse();
        }
        UserRoleResponseDto userRoleResponseDto = UserRoleMapper.mapToUserRoleResponseDto(userRole);
        log.info("Role found: {}", userRoleResponseDto);
        return ApiResponseUtils.okApiResponse(userRoleResponseDto);
    }

    @Override
    public ApiResponse<UserRoleResponseDto> updateRole(String id, UpdateUserRoleDto request) {
        log.info("Updating role: {}", request);
        UserRole userRole = userRoleRepository.findById(id).orElse(null);
        if (userRole == null) {
            log.warn("Role not found: {}", id);
            return ApiResponseUtils.notFoundApiResponse();
        }

        userRole.setActive(request.isActive());
        userRole.setDeleted(request.isDeleted());
        userRole = userRoleRepository.save(userRole);
        UserRoleResponseDto userRoleResponseDto = UserRoleMapper.mapToUserRoleResponseDto(userRole);
        log.info("Role updated: {}", userRoleResponseDto);
        return ApiResponseUtils.okApiResponse(userRoleResponseDto);
    }

    @Override
    public ApiResponse<UserRoleResponseDto> deleteRole(String id) {
        log.info("Deleting role: {}", id);
        UserRole userRole = userRoleRepository.findById(id).orElse(null);
        if (userRole == null) {
            log.warn("Role not found: {}", id);
            return ApiResponseUtils.notFoundApiResponse();
        }

        userRoleRepository.delete(userRole);
        UserRoleResponseDto userRoleResponseDto = UserRoleMapper.mapToUserRoleResponseDto(userRole);
        log.info("Role deleted: {}", userRoleResponseDto);
        return ApiResponseUtils.okApiResponse(userRoleResponseDto);
    }
}
