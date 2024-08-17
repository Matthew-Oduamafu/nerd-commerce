package io.nerdbyteslns.productcatalogservice.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Entity
public class Product {

    @Id
    @GeneratedValue(generator = "uuid")
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private String defaultImageUrl;
    private String imageUrls;

    @ManyToOne
    private Category category;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
