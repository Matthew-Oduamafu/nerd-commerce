package io.nerdbyteslns.productcatalogservice.services;

import io.nerdbyteslns.nerdcommercecore.models.reponses.ApiResponse;
import io.nerdbyteslns.productcatalogservice.dtos.requests.CreateProductDto;
import io.nerdbyteslns.productcatalogservice.dtos.responses.ProductResponseDto;

import java.util.List;

public interface ProductService {

    ApiResponse<ProductResponseDto> createProduct(CreateProductDto request);
    ApiResponse<ProductResponseDto> getProduct(String productId);
    ApiResponse<ProductResponseDto> updateProduct(String productId, CreateProductDto request);
    ApiResponse<ProductResponseDto> deleteProduct(String productId);
    ApiResponse<List<ProductResponseDto>> getAllProducts();
}
