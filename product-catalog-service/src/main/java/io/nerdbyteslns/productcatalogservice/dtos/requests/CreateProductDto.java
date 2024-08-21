package io.nerdbyteslns.productcatalogservice.dtos.requests;

import java.math.BigDecimal;

public record CreateProductDto(String name, String description, BigDecimal price,
                               String defaultImageUrl, String imageUrls, String categoryId) {
}
