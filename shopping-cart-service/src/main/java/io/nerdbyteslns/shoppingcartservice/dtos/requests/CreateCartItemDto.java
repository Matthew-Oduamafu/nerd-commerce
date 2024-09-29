package io.nerdbyteslns.shoppingcartservice.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCartItemDto {
    private String productId;
    private int quantity;
    private BigDecimal price;
}
