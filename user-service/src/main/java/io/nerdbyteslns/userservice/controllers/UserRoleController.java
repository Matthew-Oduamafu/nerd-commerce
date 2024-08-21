package io.nerdbyteslns.userservice.controllers;


import io.nerdbyteslns.nerdcommercecore.controllers.BaseController;
import io.nerdbyteslns.nerdcommercecore.models.reponses.ApiResponse;
import io.nerdbyteslns.userservice.dtos.requests.CreateUserRoleDto;
import io.nerdbyteslns.userservice.dtos.requests.UpdateUserRoleDto;
import io.nerdbyteslns.userservice.dtos.responses.UserRoleResponseDto;
import io.nerdbyteslns.userservice.services.UserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-roles")
@Slf4j
@RequiredArgsConstructor
public class UserRoleController extends BaseController {
    private final UserRoleService userRoleService;

    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json", headers = "X-API-VERSION=1")
    public ResponseEntity<ApiResponse<UserRoleResponseDto>> createUserRole(@RequestBody CreateUserRoleDto request) {
        var response = userRoleService.createRole(request);
        log.info("Creating user role with request: {} and server response: {}", request, response);
        return buildResponseEntity(response);
    }

    @GetMapping(path = "/{id}/get", produces = "application/json", headers = "X-API-VERSION=1")
    public ResponseEntity<ApiResponse<UserRoleResponseDto>> getUserRole(@PathVariable String id) {
        var response = userRoleService.getRoleById(id);
        log.info("Getting user role with id: {} and server response: {}", id, response);
        return buildResponseEntity(response);
    }

    @GetMapping(path = "/{name}/get-by-name", produces = "application/json", headers = "X-API-VERSION=1")
    public ResponseEntity<ApiResponse<UserRoleResponseDto>> getUserRoleByName(@PathVariable String name) {
        var response = userRoleService.getRoleByName(name);
        log.info("Getting user role with name: {} and server response: {}", name, response);
        return buildResponseEntity(response);
    }

    @PutMapping(path = "/{id}/update", consumes = "application/json", produces = "application/json", headers = "X-API-VERSION=1")
    public ResponseEntity<ApiResponse<UserRoleResponseDto>> updateUserRole(@PathVariable String id, @RequestBody UpdateUserRoleDto request) {
        var response = userRoleService.updateRole(id, request);
        log.info("Updating user role with id: {} and request: {} and server response: {}", id, request, response);
        return buildResponseEntity(response);
    }

    @DeleteMapping(path = "/{id}/delete", produces = "application/json", headers = "X-API-VERSION=1")
    public ResponseEntity<ApiResponse<UserRoleResponseDto>> deleteUserRole(@PathVariable String id) {
        var response = userRoleService.deleteRole(id);
        log.info("Deleting user role with id: {} and server response: {}", id, response);
        return buildResponseEntity(response);
    }

    @GetMapping(path = "/all", produces = "application/json", headers = "X-API-VERSION=1")
    public ResponseEntity<ApiResponse<List<UserRoleResponseDto>>> getAllUserRoles() {
        var response = userRoleService.getRoles();
        log.info("Getting all user roles and server response: {}", response);
        return buildResponseEntity(response);
    }
}
