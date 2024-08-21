package io.nerdbyteslns.productcatalogservice.helpers;

import io.nerdbyteslns.productcatalogservice.domains.Category;
import io.nerdbyteslns.productcatalogservice.domains.Product;
import io.nerdbyteslns.productcatalogservice.dtos.requests.CreateProductDto;
import io.nerdbyteslns.productcatalogservice.dtos.responses.ProductResponseDto;

import java.time.LocalDateTime;
import java.util.UUID;

public class ProductMapper {
    public static ProductResponseDto toProductResponse(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getDefaultImageUrl(),
                product.getImageUrls(),
                product.getCategory().getId(),
                product.getCreatedAt()
        );
    }

    public static Product toProduct(CreateProductDto request) {
        return Product.builder()
                .id(UUID.randomUUID().toString())
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .defaultImageUrl(request.defaultImageUrl())
                .imageUrls(request.imageUrls())
                .category(Category.builder().id(request.categoryId()).build())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
