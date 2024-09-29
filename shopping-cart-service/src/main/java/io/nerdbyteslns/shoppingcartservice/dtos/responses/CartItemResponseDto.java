package io.nerdbyteslns.shoppingcartservice.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemResponseDto {
    private String id;
    private String cartId;
    private String productId;
    private int quantity;
    private BigDecimal price;
    private LocalDateTime createdAt;
}
