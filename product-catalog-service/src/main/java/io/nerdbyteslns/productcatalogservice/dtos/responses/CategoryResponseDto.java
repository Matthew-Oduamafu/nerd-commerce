package io.nerdbyteslns.productcatalogservice.dtos.responses;

import java.time.LocalDateTime;

public record CategoryResponseDto(String id, String name, String description, LocalDateTime createdAt) {
}
