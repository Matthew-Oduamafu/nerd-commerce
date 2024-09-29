package io.nerdbyteslns.shoppingcartservice.services;

import io.nerdbyteslns.nerdcommercecore.models.reponses.ApiResponse;
import io.nerdbyteslns.shoppingcartservice.dtos.requests.CreateCartDto;
import io.nerdbyteslns.shoppingcartservice.dtos.requests.CreateCartItemDto;
import io.nerdbyteslns.shoppingcartservice.dtos.responses.CartResponseDto;

public interface ShoppingCartService {
    ApiResponse<CartResponseDto> createCart(CreateCartDto request);

    ApiResponse<CartResponseDto> addCartItem(String userId, CreateCartItemDto request);

    ApiResponse<CartResponseDto> updateCartItem(String userId, String itemId, CreateCartItemDto request);

    ApiResponse<CartResponseDto> removeCartItem(String userId, String itemId);

    ApiResponse<CartResponseDto> getCart(String cartId);

    ApiResponse<CartResponseDto> getCartByUser(String userId);

    ApiResponse<CartResponseDto> clearCart(String userId);
}
