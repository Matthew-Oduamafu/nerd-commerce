package io.nerdbyteslns.shoppingcartservice.repository;

import io.nerdbyteslns.shoppingcartservice.domains.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart, String> {
    // find cart by user id
    Cart findByUserId(String userId);
}
