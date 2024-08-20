package io.nerdbyteslns.productcatalogservice.repositories;

import io.nerdbyteslns.productcatalogservice.domains.Category;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class CategorySpecification {
    public static Specification<Category> createdAtBetween(LocalDateTime fromDate, LocalDateTime toDate) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("createdAt"), fromDate, toDate);
    }

    public static Specification<Category> createdAtStarts(LocalDateTime fromDate) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), fromDate);
    }

    public static Specification<Category> createdAtEnds(LocalDateTime toDate) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), toDate);
    }
}