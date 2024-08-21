package io.nerdbyteslns.productcatalogservice.services.impl;

import io.nerdbyteslns.nerdcommercecore.models.reponses.ApiResponse;
import io.nerdbyteslns.nerdcommercecore.utils.ApiResponseUtils;
import io.nerdbyteslns.productcatalogservice.domains.Category;
import io.nerdbyteslns.productcatalogservice.domains.Product;
import io.nerdbyteslns.productcatalogservice.dtos.requests.CreateProductDto;
import io.nerdbyteslns.productcatalogservice.dtos.responses.ProductResponseDto;
import io.nerdbyteslns.productcatalogservice.helpers.ProductMapper;
import io.nerdbyteslns.productcatalogservice.repositories.ProductRepository;
import io.nerdbyteslns.productcatalogservice.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public ApiResponse<ProductResponseDto> createProduct(CreateProductDto request) {
        log.info("Creating product with request: {}", request);
        Category category = Category.builder().id(request.categoryId()).build();
        boolean exists = productRepository.existsByNameAndCategory(request.name(), category);
        if (exists) {
            log.warn("Product already exists with name: {} and category: {}", request.name(), category);
            return ApiResponseUtils.badRequestApiResponse("Product already exists");
        }

        Product product = ProductMapper.toProduct(request);
        product = productRepository.save(product);
        log.info("Product created: {}", product);

        return ApiResponseUtils.okApiResponse(ProductMapper.toProductResponse(product));
    }

    @Override
    public ApiResponse<ProductResponseDto> getProduct(String productId) {
        log.info("Getting product with id: {}", productId);
        Product product = productRepository.findById(productId).orElse(null);

        if (product == null) {
            log.warn("Product not found with id: {}", productId);
            return ApiResponseUtils.notFoundApiResponse();
        }

        log.info("Product found: {}", product);
        return ApiResponseUtils.okApiResponse(ProductMapper.toProductResponse(product));
    }

    @Override
    public ApiResponse<ProductResponseDto> updateProduct(String productId, CreateProductDto request) {
        log.info("Updating product with id: {} update payload {}", productId, request);
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            log.warn("Product not found with id: {}", productId);
            return ApiResponseUtils.notFoundApiResponse();
        }

        if (productRepository.NameExistsButWithDifferentCategory(request.name(), Category.builder().id(request.categoryId()).build())) {
            log.warn("Product already exists with name: {} and category: {}", request.name(), request.categoryId());
            return ApiResponseUtils.badRequestApiResponse("Product already exists");

        }

        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setDefaultImageUrl(request.defaultImageUrl());
        product.setImageUrls(request.imageUrls());
        product.setCategory(Category.builder().id(request.categoryId()).build());
        product.setUpdatedAt(java.time.LocalDateTime.now());
        product = productRepository.save(product);

        log.info("Product updated: {}", product);
        return ApiResponseUtils.okApiResponse(ProductMapper.toProductResponse(product));
    }

    @Override
    public ApiResponse<ProductResponseDto> deleteProduct(String productId) {
        log.info("Deleting product with id: {}", productId);
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            log.warn("Product not found with id: {}", productId);
            return ApiResponseUtils.notFoundApiResponse();
        }

        productRepository.delete(product);
        log.info("Product deleted: {}", product);
        return ApiResponseUtils.okApiResponse(ProductMapper.toProductResponse(product));
    }

    @Override
    public ApiResponse<List<ProductResponseDto>> getAllProducts() {
        log.info("Getting all products");
        List<Product> products = productRepository.findAll();
        var dtos = products.stream().map(ProductMapper::toProductResponse).toList();
        log.info("All products found: {}", dtos);
        return ApiResponseUtils.okApiResponse(dtos);
    }
}
