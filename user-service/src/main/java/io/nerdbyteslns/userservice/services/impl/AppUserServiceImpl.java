package io.nerdbyteslns.userservice.services.impl;

import io.nerdbyteslns.nerdcommercecore.models.reponses.ApiResponse;
import io.nerdbyteslns.nerdcommercecore.utils.ApiResponseUtils;
import io.nerdbyteslns.userservice.domains.AppUser;
import io.nerdbyteslns.userservice.dtos.requests.CreateAppUserDto;
import io.nerdbyteslns.userservice.dtos.responses.AppUserFullResponseDto;
import io.nerdbyteslns.userservice.dtos.responses.AppUserResponseDto;
import io.nerdbyteslns.userservice.helpers.AppUserMapper;
import io.nerdbyteslns.userservice.repositories.AppUserRepository;
import io.nerdbyteslns.userservice.services.AppUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;


    @Override
    public ApiResponse<AppUserResponseDto> createUser(CreateAppUserDto request) {
        log.info("Creating user with email: {}, and request {}", request.email(), request);
        if(appUserRepository.findByEmailOrPhone(request.email(), request.phone()) != null) {
            log.warn("User with email or phone already exists");
            return ApiResponseUtils.badRequestApiResponse("User with email or phone already exists");
        }

        AppUser appUser = AppUserMapper.mapToAppUser(request);
        appUser = appUserRepository.save(appUser);
        log.info("User created successfully with email: {}", appUser.getEmail());
        return ApiResponseUtils.okApiResponse(AppUserMapper.mapToAppUserResponseDto(appUser));
    }

    @Override
    public ApiResponse<AppUserResponseDto> getUserById(String id) {
        log.info("Finding user with id: {}", id);
        AppUser appUser = appUserRepository.findById(id).orElse(null);
        if(appUser == null) {
            log.warn("User with id: {} not found", id);
            return ApiResponseUtils.notFoundApiResponse();
        }
        log.info("User with id: {} found", id);
        return ApiResponseUtils.okApiResponse(AppUserMapper.mapToAppUserResponseDto(appUser));
    }

    @Override
    public ApiResponse<AppUserResponseDto> getUserByEmail(String email) {
        log.info("Finding user with email: {}", email);
        AppUser appUser = appUserRepository.findByEmail(email);
        if(appUser == null) {
            log.warn("User with email: {} not found", email);
            return ApiResponseUtils.notFoundApiResponse();
        }
        log.info("User with email: {} found", email);
        return ApiResponseUtils.okApiResponse(AppUserMapper.mapToAppUserResponseDto(appUser));
    }

    @Override
    public ApiResponse<AppUserFullResponseDto> getUserFullDetailsByEmail(String email) {
        log.info("Finding user with email: {}", email);
        AppUser appUser = appUserRepository.findByEmail(email);
        if(appUser == null) {
            log.warn("User with email: {} not found", email);
            return ApiResponseUtils.notFoundApiResponse();
        }
        log.info("User with email: {} found", email);
        return ApiResponseUtils.okApiResponse(AppUserMapper.mapToAppUserFullResponseDto(appUser));
    }

    @Override
    public ApiResponse<AppUserResponseDto> deleteUser(String id) {
        log.info("Deleting user with id: {}", id);
        AppUser appUser = appUserRepository.findById(id).orElse(null);
        if(appUser == null) {
            log.warn("User with id: {} not found", id);
            return ApiResponseUtils.notFoundApiResponse();
        }
        appUserRepository.delete(appUser);
        log.info("User with id: {} deleted", id);
        return ApiResponseUtils.okApiResponse(AppUserMapper.mapToAppUserResponseDto(appUser));
    }
}
