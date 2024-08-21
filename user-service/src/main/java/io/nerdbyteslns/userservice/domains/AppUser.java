package io.nerdbyteslns.userservice.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class AppUser {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private boolean isActive;
    private boolean isDeleted;
    private boolean isEmailVerified;
    private boolean isPhoneVerified;
    private String password;

    @ManyToMany(mappedBy = "appUsers")
    private List<UserRole> roles;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
