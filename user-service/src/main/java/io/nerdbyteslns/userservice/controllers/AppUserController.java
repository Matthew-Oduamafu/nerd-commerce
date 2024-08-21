package io.nerdbyteslns.userservice.controllers;

import io.nerdbyteslns.nerdcommercecore.controllers.BaseController;
import io.nerdbyteslns.nerdcommercecore.models.reponses.ApiResponse;
import io.nerdbyteslns.userservice.dtos.requests.CreateAppUserDto;
import io.nerdbyteslns.userservice.dtos.responses.AppUserFullResponseDto;
import io.nerdbyteslns.userservice.dtos.responses.AppUserResponseDto;
import io.nerdbyteslns.userservice.services.AppUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/users")
public class AppUserController extends BaseController {
    private final AppUserService appUserService;

    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json", headers = "X-API-VERSION=1")
    public ResponseEntity<ApiResponse<AppUserResponseDto>> createUser(CreateAppUserDto request) {
        var response = appUserService.createUser(request);
        log.info("Creating user with request: {} and server response: {}", request, response);
        return buildResponseEntity(response);
    }

    @GetMapping(path = "/{id}/get", produces = "application/json", headers = "X-API-VERSION=1")
    public ResponseEntity<ApiResponse<AppUserResponseDto>> getUser(@PathVariable String id) {
        var response = appUserService.getUserById(id);
        log.info("Getting user with id: {} and server response: {}", id, response);
        return buildResponseEntity(response);
    }

    @GetMapping(path = "/{email}/get-by-email", produces = "application/json", headers = "X-API-VERSION=1")
    public ResponseEntity<ApiResponse<AppUserResponseDto>> getUserByEmail(@PathVariable String email) {
        var response = appUserService.getUserByEmail(email);
        log.info("Getting user with email: {} and server response: {}", email, response);
        return buildResponseEntity(response);
    }

    @GetMapping(path = "/{email}/full-details", produces = "application/json", headers = "X-API-VERSION=1")
    public ResponseEntity<ApiResponse<AppUserFullResponseDto>> getUserFullDetailsByEmail(@PathVariable String email) {
        var response = appUserService.getUserFullDetailsByEmail(email);
        log.info("Getting user with email: {} and server response: {}", email, response);
        return buildResponseEntity(response);
    }

    @DeleteMapping(path = "/{id}/delete", produces = "application/json", headers = "X-API-VERSION=1")
    public ResponseEntity<ApiResponse<AppUserResponseDto>> deleteUser(@PathVariable String id) {
        var response = appUserService.deleteUser(id);
        log.info("Deleting user with id: {} and server response: {}", id, response);
        return buildResponseEntity(response);
    }
}
