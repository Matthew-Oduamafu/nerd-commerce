package io.nerdbyteslns.userservice.domains;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class UserRole {

    @Id
    private String id;
    private String name;
    private String description;
    private boolean isActive;
    private boolean isDeleted;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    List<AppUser> appUsers;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
