package io.nerdbyteslns.productcatalogservice.controllers;


import io.nerdbyteslns.nerdcommercecore.models.PagedList;
import io.nerdbyteslns.nerdcommercecore.models.reponses.ApiResponse;
import io.nerdbyteslns.productcatalogservice.dtos.requests.CreateCategoryDto;
import io.nerdbyteslns.productcatalogservice.dtos.responses.CategoryResponseDto;
import io.nerdbyteslns.productcatalogservice.models.CategoryFilter;
import io.nerdbyteslns.productcatalogservice.services.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController()
@RequestMapping("/api/categories")
public class CategoryController extends BaseController {

    private final CategoryService categoryService;

    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json", headers = "X-API-VERSION=1")
    public ResponseEntity<ApiResponse<CategoryResponseDto>> createCategory(@RequestBody CreateCategoryDto request) {
        var response = categoryService.createCategory(request);
        log.info("Creating category with request: {} and server response: {}", request, response);
        return buildResponseEntity(response);
    }

    @PutMapping(path = "{id}/update", consumes = "application/json", produces = "application/json", headers = "X-API-VERSION=1")
    public ResponseEntity<ApiResponse<CategoryResponseDto>> updateCategory(@PathVariable String id, @RequestBody CreateCategoryDto request) {
        var response = categoryService.updateCategory(id, request);
        log.info("Updating category with request: {} and server response: {}", request, response);
        return buildResponseEntity(response);
    }

    @GetMapping(produces = "application/json", headers = "X-API-VERSION=1")
    public ResponseEntity<ApiResponse<PagedList<CategoryResponseDto>>> getCategories(@RequestParam(defaultValue = "1") int page,
                                                                                     @RequestParam(defaultValue = "10") int pageSize,
                                                                                     @RequestParam(required = false) LocalDateTime fromDate,
                                                                                     @RequestParam(required = false) LocalDateTime toDate,
                                                                                     @RequestParam(required = false, defaultValue = "asc") String sortDir) {
        CategoryFilter filter = new CategoryFilter(page, pageSize, fromDate, toDate, sortDir);
        var response = categoryService.getCategories(filter);
        log.info("Getting categories with filter: {} and server response: {}", filter, response);
        return buildResponseEntity(response);
    }

    @GetMapping(path = "all", produces = "application/json", headers = "X-API-VERSION=1")
    public ResponseEntity<ApiResponse<List<CategoryResponseDto>>> getCategory() {
        var response = categoryService.getCategories();
        log.info("Getting category and server response: {}", response);
        return buildResponseEntity(response);
    }
}
