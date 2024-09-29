package io.nerdbyteslns.shoppingcartservice.services.impl;

import io.nerdbyteslns.nerdcommercecore.models.reponses.ApiResponse;
import io.nerdbyteslns.nerdcommercecore.utils.ApiResponseUtils;
import io.nerdbyteslns.shoppingcartservice.domains.Cart;
import io.nerdbyteslns.shoppingcartservice.domains.CartItem;
import io.nerdbyteslns.shoppingcartservice.dtos.requests.CreateCartDto;
import io.nerdbyteslns.shoppingcartservice.dtos.requests.CreateCartItemDto;
import io.nerdbyteslns.shoppingcartservice.dtos.responses.CartResponseDto;
import io.nerdbyteslns.shoppingcartservice.helpers.CartItemMapper;
import io.nerdbyteslns.shoppingcartservice.helpers.CartMapper;
import io.nerdbyteslns.shoppingcartservice.repository.CartRepository;
import io.nerdbyteslns.shoppingcartservice.services.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
@RequiredArgsConstructor
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final CartRepository cartRepository;

    @Override
    public ApiResponse<CartResponseDto> createCart(CreateCartDto request) {
        log.info("Creating cart for user {}, with payload {}", request.getUserId(), request);

        // check if cart already exists for user
        Cart existingCart = cartRepository.findByUserId(request.getUserId());

        if (existingCart != null) {
            existingCart.getItems().addAll(CartMapper.toCartItems(request.getItems(), existingCart));
        } else {
            existingCart = CartMapper.toCart(request);
        }

        // create Cart
        Cart cart = cartRepository.save(existingCart);

        log.info("Cart created successfully with id {}, response {}", cart.getId(), cart);
        CartResponseDto cartResponseDto = CartMapper.toCartResponseDto(cart);

        return ApiResponseUtils.createApiResponse(cartResponseDto);
    }

    @Override
    public ApiResponse<CartResponseDto> addCartItem(String userId, CreateCartItemDto request) {
        log.info("Adding item to cart with id {}, with payload {}", userId, request);

        // get cart
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            log.error("Cart with id {} not found", userId);
            return ApiResponseUtils.notFoundApiResponse("Cart not found");
        }

        // create CartItem
        CartItem cartItem = CartItemMapper.toCartItem(request, cart.getId());
        if (cart.getItems() == null) {
            cart.setItems(new ArrayList<>());
        }
        cart.getItems().add(cartItem);
        cart = cartRepository.save(cart);

        log.info("Item added to cart with id {}, response {}", cart.getId(), cart);
        CartResponseDto cartResponseDto = CartMapper.toCartResponseDto(cart);

        return ApiResponseUtils.createApiResponse(cartResponseDto);
    }

    @Override
    public ApiResponse<CartResponseDto> updateCartItem(String userId, String itemId, CreateCartItemDto request) {
        log.info("Updating item with id {} in cart with id {}, with payload {}", itemId, userId, request);

        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            log.error("Cart with id {} not found", userId);
            return ApiResponseUtils.notFoundApiResponse("Cart not found");
        }

        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElse(null);
        if (cartItem == null) {
            log.error("Item with id {} not found", itemId);
            return ApiResponseUtils.notFoundApiResponse("Cart item not found");
        }

        cartItem.setProductId(request.getProductId());
        cartItem.setQuantity(request.getQuantity());
        cartItem.setPrice(request.getPrice());

        cart.getItems().replaceAll(item -> item.getId().equals(itemId) ? cartItem : item);
        cart = cartRepository.save(cart);

        log.info("Item updated in cart with id {}, response {}", cart.getId(), cart);
        CartResponseDto cartResponseDto = CartMapper.toCartResponseDto(cart);

        return ApiResponseUtils.createApiResponse(cartResponseDto);
    }

    @Override
    public ApiResponse<CartResponseDto> removeCartItem(String userId, String itemId) {
        log.info("Removing item with id {} from cart with user id {}", itemId, userId);

        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            log.error("Cart with id {} not found", userId);
            return ApiResponseUtils.notFoundApiResponse("Cart not found");
        }

        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElse(null);
        if (cartItem == null) {
            log.error("Item with id {} not found", itemId);
            return ApiResponseUtils.notFoundApiResponse("Cart item not found");
        }

        cart.getItems().remove(cartItem);
        cart = cartRepository.save(cart);

        log.info("Item removed from cart with id {}, response {}", cart.getId(), cart);
        CartResponseDto cartResponseDto = CartMapper.toCartResponseDto(cart);

        return ApiResponseUtils.createApiResponse(cartResponseDto);
    }

    @Override
    public ApiResponse<CartResponseDto> getCart(String cartId) {

        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null) {
            log.error("Cart with id {} not found", cartId);
            return ApiResponseUtils.notFoundApiResponse("Cart not found");
        }

        log.info("Cart with id {} found", cartId);
        CartResponseDto cartResponseDto = CartMapper.toCartResponseDto(cart);

        return ApiResponseUtils.createApiResponse(cartResponseDto);
    }

    @Override
    public ApiResponse<CartResponseDto> getCartByUser(String userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            log.error("Cart with user id {} not found", userId);
            return ApiResponseUtils.notFoundApiResponse("Cart not found");
        }

        log.info("Cart with user id {} found", userId);
        CartResponseDto cartResponseDto = CartMapper.toCartResponseDto(cart);

        return ApiResponseUtils.createApiResponse(cartResponseDto);
    }

    @Override
    public ApiResponse<CartResponseDto> clearCart(String userId) {
        log.info("Clearing cart with user id {}", userId);

        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            log.error("Cart with user id {} not found", userId);
            return ApiResponseUtils.notFoundApiResponse("Cart not found");
        }

        cart.getItems().clear();
        cart = cartRepository.save(cart);

        log.info("Cart with user id {} cleared", userId);
        CartResponseDto cartResponseDto = CartMapper.toCartResponseDto(cart);

        return ApiResponseUtils.createApiResponse(cartResponseDto);
    }
}
