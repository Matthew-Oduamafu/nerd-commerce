package io.nerdbyteslns.productcatalogservice.controllers;


import io.nerdbyteslns.nerdcommercecore.models.reponses.ApiResponse;
import io.nerdbyteslns.productcatalogservice.dtos.requests.CreateProductDto;
import io.nerdbyteslns.productcatalogservice.dtos.responses.ProductResponseDto;
import io.nerdbyteslns.productcatalogservice.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController extends BaseController {

    private final ProductService productService;

    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json", headers = "X-API-VERSION=1")
    public ResponseEntity<ApiResponse<ProductResponseDto>> createProduct(@RequestBody CreateProductDto request) {
        var response = productService.createProduct(request);
        log.info("Creating product with request: {} and server response: {}", request, response);
        return buildResponseEntity(response);
    }

    @GetMapping(path = "/{id}/get", produces = "application/json", headers = "X-API-VERSION=1")
    public ResponseEntity<ApiResponse<ProductResponseDto>> getProduct(@PathVariable String id) {
        var response = productService.getProduct(id);
        log.info("Getting product with id: {} and server response: {}", id, response);
        return buildResponseEntity(response);
    }

    @PutMapping(path = "/{id}/update", consumes = "application/json", produces = "application/json", headers = "X-API-VERSION=1")
    public ResponseEntity<ApiResponse<ProductResponseDto>> updateProduct(@PathVariable String id, @RequestBody CreateProductDto request) {
        var response =  productService.updateProduct(id, request);
        log.info("Updating product with id: {} and request: {} and server response: {}", id, request, response);
        return buildResponseEntity(response);
    }

    @DeleteMapping(path = "/{id}/delete", produces = "application/json", headers = "X-API-VERSION=1")
    public ResponseEntity<ApiResponse<ProductResponseDto>> deleteProduct(@PathVariable String id) {
        var response = productService.deleteProduct(id);
        log.info("Deleting product with id: {} and server response: {}", id, response);
        return buildResponseEntity(response);
    }

    @GetMapping(path = "/all", produces = "application/json", headers = "X-API-VERSION=1")
    public ResponseEntity<ApiResponse<List<ProductResponseDto>>> getAllProducts() {
        var response = productService.getAllProducts();
        log.info("Getting all products and server response: {}", response);
        return buildResponseEntity(response);
    }
}
