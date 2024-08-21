package io.nerdbyteslns.userservice.repositories;

import io.nerdbyteslns.userservice.domains.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    AppUser findByEmail(String email);
    AppUser findByPhone(String phone);
    AppUser findByEmailOrPhone(String email, String phone);
}
