package io.nerdbyteslns.nerdcommercecore.utils;

import io.nerdbyteslns.nerdcommercecore.enums.DefaultHttpMessages;
import io.nerdbyteslns.nerdcommercecore.models.ErrorDetails;
import io.nerdbyteslns.nerdcommercecore.models.reponses.ApiResponse;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ApiResponseUtils {
    public static <T> ApiResponse<T> createApiResponse(T data) {
        return ApiResponseUtils.toApiResponse(DefaultHttpMessages.CREATED.getMessage(), true, HttpStatus.CREATED.value(), data, null);
    }

    public static <T> ApiResponse<T> okApiResponse(T data) {
        return ApiResponseUtils.toApiResponse(DefaultHttpMessages.SUCCESS.getMessage(), true, HttpStatus.OK.value(), data, null);
    }

    public static <T> ApiResponse<T> acceptApiResponse(T data) {
        return ApiResponseUtils.toApiResponse(DefaultHttpMessages.ACCEPTED.getMessage(), true, HttpStatus.ACCEPTED.value(), data, null);
    }

    public static <T> ApiResponse<T> badRequestApiResponse() {
        return ApiResponseUtils.toApiResponse(DefaultHttpMessages.BAD_REQUEST.getMessage(), false, HttpStatus.BAD_REQUEST.value(), null, null);
    }

    public static <T> ApiResponse<T> badRequestApiResponse(String message) {
        return ApiResponseUtils.toApiResponse(message, false, HttpStatus.BAD_REQUEST.value(), null, null);
    }

    public static <T> ApiResponse<T> badRequestApiResponse(String message, List<ErrorDetails> errors) {
        return ApiResponseUtils.toApiResponse(message, false, HttpStatus.BAD_REQUEST.value(), null, errors);
    }

    // failedDependencyApiResponse
    public static <T> ApiResponse<T> failedDependencyApiResponse() {
        return ApiResponseUtils.toApiResponse(DefaultHttpMessages.FAILED_DEPENDENCY.getMessage(), false, HttpStatus.FAILED_DEPENDENCY.value(), null, null);
    }

    public static <T> ApiResponse<T> failedDependencyApiResponse(String message) {
        return ApiResponseUtils.toApiResponse(message, false, HttpStatus.FAILED_DEPENDENCY.value(), null, null);
    }

    public static <T> ApiResponse<T> failedDependencyApiResponse(String message, List<ErrorDetails> errors) {
        return ApiResponseUtils.toApiResponse(message, false, HttpStatus.FAILED_DEPENDENCY.value(), null, null);
    }


    // notFoundApiResponse
    public static <T> ApiResponse<T> notFoundApiResponse() {
        return ApiResponseUtils.toApiResponse(DefaultHttpMessages.NOT_FOUND.getMessage(), false, HttpStatus.NOT_FOUND.value(), null, null);
    }


    // notFoundApiResponse
    public static <T> ApiResponse<T> notFoundApiResponse(String message) {
        return ApiResponseUtils.toApiResponse(message, false, HttpStatus.NOT_FOUND.value(), null, null);
    }

    // internalServerErrorApiResponse
    public static <T> ApiResponse<T> internalServerErrorApiResponse() {
        return ApiResponseUtils.toApiResponse(DefaultHttpMessages.INTERNAL_SERVER_ERROR.getMessage(), false, HttpStatus.INTERNAL_SERVER_ERROR.value(), null, null);
    }

    public static <T> ApiResponse<T> internalServerErrorApiResponse(String message) {
        return ApiResponseUtils.toApiResponse(message, false, HttpStatus.INTERNAL_SERVER_ERROR.value(), null, null);
    }

    public static <T> ApiResponse<T> internalServerErrorApiResponse(String message, List<ErrorDetails> errors) {
        return ApiResponseUtils.toApiResponse(message, false, HttpStatus.INTERNAL_SERVER_ERROR.value(), null, errors);
    }

    // unauthorizedApiResponse
    public static <T> ApiResponse<T> unauthorizedApiResponse() {
        return ApiResponseUtils.toApiResponse(DefaultHttpMessages.UNAUTHORIZED.getMessage(), false, HttpStatus.UNAUTHORIZED.value(), null, null);
    }

    // forbiddenApiResponse
    public static <T> ApiResponse<T> forbiddenApiResponse() {
        return ApiResponseUtils.toApiResponse(DefaultHttpMessages.FORBIDDEN.getMessage(), false, HttpStatus.FORBIDDEN.value(), null, null);
    }

    // conflictApiResponse
    public static <T> ApiResponse<T> conflictApiResponse() {
        return ApiResponseUtils.toApiResponse(DefaultHttpMessages.CONFLICT.getMessage(), false, HttpStatus.CONFLICT.value(), null, null);
    }


    public static <T> ApiResponse<T> toApiResponse(String message, Boolean success, int statusCode, T data, List<ErrorDetails> errors) {
        return ApiResponse.<T>builder()
                .message(message)
                .success(success)
                .statusCode(statusCode)
                .data(data)
                .errors(errors)
                .build();
    }
}
