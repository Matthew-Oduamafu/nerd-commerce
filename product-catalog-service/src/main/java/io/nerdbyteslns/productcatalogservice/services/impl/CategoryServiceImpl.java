package io.nerdbyteslns.productcatalogservice.services.impl;

import io.nerdbyteslns.nerdcommercecore.models.PagedList;
import io.nerdbyteslns.nerdcommercecore.models.reponses.ApiResponse;
import io.nerdbyteslns.nerdcommercecore.utils.ApiResponseUtils;
import io.nerdbyteslns.productcatalogservice.domains.Category;
import io.nerdbyteslns.productcatalogservice.dtos.requests.CreateCategoryDto;
import io.nerdbyteslns.productcatalogservice.dtos.responses.CategoryResponseDto;
import io.nerdbyteslns.productcatalogservice.helpers.CategoryMapper;
import io.nerdbyteslns.productcatalogservice.models.CategoryFilter;
import io.nerdbyteslns.productcatalogservice.repositories.CategoryRepository;
import io.nerdbyteslns.productcatalogservice.repositories.CategorySpecification;
import io.nerdbyteslns.productcatalogservice.services.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public ApiResponse<CategoryResponseDto> createCategory(CreateCategoryDto request) {
        log.info("Creating category wih request: {}", request);

        if (categoryRepository.existsByName(request.name())) {
            log.warn("Category with name '{}' already exists", request.name());
            return ApiResponseUtils.badRequestApiResponse("Category with name '" + request.name() + "' already exists");
        }

        Category category = CategoryMapper.toCategory(request);
        category = categoryRepository.save(category);
        CategoryResponseDto categoryResponseDto = CategoryMapper.toCategoryResponse(category);
        log.info("Category created successfully: {}", category);
        return ApiResponseUtils.okApiResponse(categoryResponseDto);
    }

    @Override
    public ApiResponse<CategoryResponseDto> updateCategory(String id, CreateCategoryDto request) {
        log.info("Updating category with id: {}, with payload: {}", id, request);
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            log.warn("Category with id '{}' not found", id);
            return ApiResponseUtils.notFoundApiResponse();
        }

        if (categoryRepository.nameExistWithDifferentId(request.name(), id)) {
            log.warn("Category with name '{}' already exists", request.name());
            return ApiResponseUtils.badRequestApiResponse("Category with name '" + request.name() + "' already exists");
        }

        category.setName(request.name());
        category.setDescription(request.description());
        category.setUpdatedAt(LocalDateTime.now());

        category = categoryRepository.save(category);

        CategoryResponseDto categoryResponseDto = CategoryMapper.toCategoryResponse(category);
        log.info("Category updated successfully: {}", category);
        return ApiResponseUtils.acceptApiResponse(categoryResponseDto);
    }

    @Override
    public ApiResponse<PagedList<CategoryResponseDto>> getCategories(CategoryFilter filter) {
        log.info("Fetching categories with filter: {}", filter);

        Sort sort;
        if (filter.getSortDir().equalsIgnoreCase("desc")) {
            sort = Sort.by(Sort.Order.desc("createdAt"));
        } else {
            sort = Sort.by(Sort.Order.asc("createdAt"));
        }
        PageRequest pageable = PageRequest.of(filter.getPage(), filter.getPageSize(), sort);
        Specification<Category> spec;

        if (filter.getFromDate() != null && filter.getToDate() != null) {
            spec = Specification.where(CategorySpecification.createdAtBetween(filter.getFromDate(), filter.getToDate()));
        } else if (filter.getFromDate() != null) {
            spec = Specification.where(CategorySpecification.createdAtStarts(filter.getFromDate()));
        } else if (filter.getToDate() != null) {
            spec = Specification.where(CategorySpecification.createdAtEnds(filter.getToDate()));
        } else {
            spec = Specification.where(null);
        }

        var result = categoryRepository.findAll(spec, pageable);

        var dtos = result.getContent().stream().map(CategoryMapper::toCategoryResponse).toList();

        var response = new PagedList<>(filter.getPage(), filter.getPageSize(), result.getTotalElements(), dtos);

        log.info("Categories fetched successfully: {}", response);
        return ApiResponseUtils.okApiResponse(response);
    }

    @Override
    public ApiResponse<List<CategoryResponseDto>> getCategories() {
        var all = categoryRepository.findAll();
        var dtos = all.stream().map(CategoryMapper::toCategoryResponse).toList();
        return ApiResponseUtils.okApiResponse(dtos);
    }
}
