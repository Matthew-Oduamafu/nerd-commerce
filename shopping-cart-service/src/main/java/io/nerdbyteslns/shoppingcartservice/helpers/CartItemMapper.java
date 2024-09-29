package io.nerdbyteslns.shoppingcartservice.helpers;

import io.nerdbyteslns.shoppingcartservice.domains.CartItem;
import io.nerdbyteslns.shoppingcartservice.dtos.requests.CreateCartItemDto;
import io.nerdbyteslns.shoppingcartservice.dtos.responses.CartItemResponseDto;

import java.time.LocalDateTime;
import java.util.UUID;

public class CartItemMapper {

    // CartItem from CreateCartItemDto
    public static CartItem toCartItem(CreateCartItemDto dto, String cartId) {
        return CartItem.builder()
                .id(UUID.randomUUID().toString())
                .cartId(cartId)
                .productId(dto.getProductId())
                .quantity(dto.getQuantity())
                .price(dto.getPrice())
                .createdAt(LocalDateTime.now())
                .build();
    }

    // CartItemResponseDto from CartItem
    public static CartItemResponseDto toCartItemResponseDto(CartItem cartItem) {
        return CartItemResponseDto.builder()
                .id(cartItem.getId())
                .cartId(cartItem.getCartId())
                .productId(cartItem.getProductId())
                .quantity(cartItem.getQuantity())
                .price(cartItem.getPrice())
                .createdAt(cartItem.getCreatedAt())
                .build();
    }
}
