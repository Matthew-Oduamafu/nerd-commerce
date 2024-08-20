package io.nerdbyteslns.productcatalogservice.controllers;

import io.nerdbyteslns.nerdcommercecore.models.reponses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public abstract class BaseController {

    // generic methods to be used to return ResponseEntity<ApiResponse<>> given ApiResponse<>
    protected <T> ResponseEntity<ApiResponse<T>> buildResponseEntity(ApiResponse<T> apiResponse) {
        return new ResponseEntity<>(apiResponse, HttpStatusCode.valueOf(apiResponse.getStatusCode()));
    }

}
