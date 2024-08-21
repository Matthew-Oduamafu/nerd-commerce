package io.nerdbyteslns.userservice.repositories;

import io.nerdbyteslns.userservice.domains.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String>{
    boolean existsByName(String name);

    UserRole findByName(String name);
}
