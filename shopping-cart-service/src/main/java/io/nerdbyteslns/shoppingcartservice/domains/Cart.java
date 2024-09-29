package io.nerdbyteslns.shoppingcartservice.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash("Carts")
public class Cart implements Serializable {
    private String id;

    private List<CartItem> items;
    @Indexed
    private String userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
