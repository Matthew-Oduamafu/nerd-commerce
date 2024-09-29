package io.nerdbyteslns.shoppingcartservice.controllers;


import io.nerdbyteslns.nerdcommercecore.controllers.BaseController;
import io.nerdbyteslns.nerdcommercecore.models.reponses.ApiResponse;
import io.nerdbyteslns.shoppingcartservice.dtos.requests.CreateCartDto;
import io.nerdbyteslns.shoppingcartservice.dtos.requests.CreateCartItemDto;
import io.nerdbyteslns.shoppingcartservice.dtos.responses.CartResponseDto;
import io.nerdbyteslns.shoppingcartservice.services.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController extends BaseController {

    private final ShoppingCartService shoppingCartService;

    // create cart
    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json", headers = "X-API-VERSION=1")
    public ResponseEntity<ApiResponse<CartResponseDto>> createCart(@RequestBody CreateCartDto request) {
        var response = shoppingCartService.createCart(request);
        log.info("Creating cart with request: {} and server response: {}", request, response);
        return buildResponseEntity(response);
    }

    // add item to cart
    @PostMapping(path = "/{userId}/add-item", consumes = "application/json", produces = "application/json", headers = "X-API-VERSION=1")
    public ResponseEntity<ApiResponse<CartResponseDto>> addCartItem(@PathVariable String userId, @RequestBody CreateCartItemDto request) {
        var response = shoppingCartService.addCartItem(userId, request);
        log.info("Adding item to cart with cart for user {}, request: {} and server response: {}", userId, request, response);
        return buildResponseEntity(response);
    }

    // update item in cart
    @PutMapping(path = "/{userId}/update-item/{itemId}", consumes = "application/json", produces = "application/json", headers = "X-API-VERSION=1")
    public ResponseEntity<ApiResponse<CartResponseDto>> updateCartItem(@PathVariable String userId, @PathVariable String itemId, @RequestBody CreateCartItemDto request) {
        var response = shoppingCartService.updateCartItem(userId, itemId, request);
        log.info("Updating item in cart with cart for user {}, item id {}, request: {} and server response: {}", userId, itemId, request, response);
        return buildResponseEntity(response);
    }

    // remove item from cart
    @DeleteMapping(path = "/{userId}/remove-item/{itemId}", produces = "application/json", headers = "X-API-VERSION=1")
    public ResponseEntity<ApiResponse<CartResponseDto>> removeCartItem(@PathVariable String userId, @PathVariable String itemId) {
        var response = shoppingCartService.removeCartItem(userId, itemId);
        log.info("Removing item from cart with cart for user {}, item id {} and server response: {}", userId, itemId, response);
        return buildResponseEntity(response);
    }

    // get cart by id
    @GetMapping(path = "/{cartId}", produces = "application/json", headers = "X-API-VERSION=1")
    public ResponseEntity<ApiResponse<CartResponseDto>> getCart(@PathVariable String cartId) {
        var response = shoppingCartService.getCart(cartId);
        log.info("Getting cart with id {} and server response: {}", cartId, response);
        return buildResponseEntity(response);
    }

    // get cart by user
    @GetMapping(path = "/user/{userId}", produces = "application/json", headers = "X-API-VERSION=1")
    public ResponseEntity<ApiResponse<CartResponseDto>> getCartByUser(@PathVariable String userId) {
        var response = shoppingCartService.getCartByUser(userId);
        log.info("Getting cart with user id {} and server response: {}", userId, response);
        return buildResponseEntity(response);
    }

    // clear cart
    @DeleteMapping(path = "/{userId}/clear", produces = "application/json", headers = "X-API-VERSION=1")
    public ResponseEntity<ApiResponse<CartResponseDto>> clearCart(@PathVariable String userId) {
        var response = shoppingCartService.clearCart(userId);
        log.info("Clearing cart with user id {} and server response: {}", userId, response);
        return buildResponseEntity(response);
    }
}
