package io.nerdbyteslns.productcatalogservice.domains;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Entity
public class Category {

    @Id
    @GeneratedValue(generator = "uuid")
    private String id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Product> products;

    private String createdAt;
    private String updatedAt;
}
