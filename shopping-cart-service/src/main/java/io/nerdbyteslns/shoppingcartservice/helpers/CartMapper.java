package io.nerdbyteslns.shoppingcartservice.helpers;

import io.nerdbyteslns.shoppingcartservice.domains.Cart;
import io.nerdbyteslns.shoppingcartservice.domains.CartItem;
import io.nerdbyteslns.shoppingcartservice.dtos.requests.CreateCartDto;
import io.nerdbyteslns.shoppingcartservice.dtos.requests.CreateCartItemDto;
import io.nerdbyteslns.shoppingcartservice.dtos.responses.CartResponseDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CartMapper {

    // Cart from CreateCartDto
    public static Cart toCart(CreateCartDto dto) {
        String cartId = UUID.randomUUID().toString();
        return Cart.builder()
                .id(cartId)
                .items(toCartItems(dto.getItems(), cartId))
                .userId(dto.getUserId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    // CartResponseDto from Cart
    public static CartResponseDto toCartResponseDto(Cart cart) {
        return CartResponseDto.builder()
                .id(cart.getId())
                .items(cart.getItems().stream()
                        .map(CartItemMapper::toCartItemResponseDto)
                        .collect(Collectors.toList()))
                .userId(cart.getUserId())
                .createdAt(cart.getCreatedAt())
                .build();
    }


    public static List<CartItem> toCartItems(List<CreateCartItemDto> dtos, String cartId) {
        return dtos.stream()
                .map(dto -> CartItemMapper.toCartItem(dto, cartId))
                .collect(Collectors.toList());
    }

    public static List<CartItem> toCartItems(List<CreateCartItemDto> dtos, Cart cart) {
        return dtos.stream()
                .map(dto -> CartItemMapper.toCartItem(dto, cart.getId()))
                .collect(Collectors.toList());
    }
}
