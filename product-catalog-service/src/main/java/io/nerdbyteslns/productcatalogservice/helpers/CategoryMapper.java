package io.nerdbyteslns.productcatalogservice.helpers;

import io.nerdbyteslns.productcatalogservice.domains.Category;
import io.nerdbyteslns.productcatalogservice.dtos.requests.CreateCategoryDto;
import io.nerdbyteslns.productcatalogservice.dtos.responses.CategoryResponseDto;

import java.time.LocalDateTime;
import java.util.UUID;

public class CategoryMapper {
    public static CategoryResponseDto toCategoryResponse(Category category) {
        return new CategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getCreatedAt()
        );
    }

    public static Category toCategory(CreateCategoryDto request) {
        return Category.builder()
                .id(UUID.randomUUID().toString())
                .name(request.name())
                .description(request.description())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
