package io.nerdbyteslns.productcatalogservice.services;

import io.nerdbyteslns.nerdcommercecore.models.PagedList;
import io.nerdbyteslns.nerdcommercecore.models.reponses.ApiResponse;
import io.nerdbyteslns.productcatalogservice.dtos.requests.CreateCategoryDto;
import io.nerdbyteslns.productcatalogservice.dtos.responses.CategoryResponseDto;
import io.nerdbyteslns.productcatalogservice.models.CategoryFilter;

import java.util.List;

public interface CategoryService {

    ApiResponse<CategoryResponseDto> createCategory(CreateCategoryDto request);
    ApiResponse<CategoryResponseDto> updateCategory(String id, CreateCategoryDto request);
    ApiResponse<PagedList<CategoryResponseDto>> getCategories(CategoryFilter filter);
    ApiResponse<List<CategoryResponseDto>> getCategories();
}
