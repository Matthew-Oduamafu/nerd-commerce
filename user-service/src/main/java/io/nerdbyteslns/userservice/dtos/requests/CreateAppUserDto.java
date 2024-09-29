package io.nerdbyteslns.userservice.dtos.requests;

public record CreateAppUserDto(String firstName, String lastName, String email, String phone, String password) {
}
