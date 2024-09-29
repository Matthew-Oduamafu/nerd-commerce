package io.nerdbyteslns.shoppingcartservice.dtos.requests;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CreateCartDto {
    private List<CreateCartItemDto> items;
    private String userId;
}
