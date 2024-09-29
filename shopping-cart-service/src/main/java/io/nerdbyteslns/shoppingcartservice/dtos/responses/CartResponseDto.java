package io.nerdbyteslns.shoppingcartservice.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponseDto {
    private String id;
    private List<CartItemResponseDto> items;
    private String userId;
    private LocalDateTime createdAt;
}
