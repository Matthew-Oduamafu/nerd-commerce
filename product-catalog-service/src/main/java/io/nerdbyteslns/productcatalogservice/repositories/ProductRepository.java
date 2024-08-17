package io.nerdbyteslns.productcatalogservice.repositories;


import io.nerdbyteslns.productcatalogservice.domains.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
}
