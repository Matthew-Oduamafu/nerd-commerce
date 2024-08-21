package io.nerdbyteslns.productcatalogservice.dtos.responses;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponseDto(String id, String name, String description, BigDecimal price,
                                 String defaultImageUrl, String imageUrls, String categoryId,
                                 LocalDateTime createdAt) {
}
