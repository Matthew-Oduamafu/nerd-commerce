package io.nerdbyteslns.productcatalogservice.repositories;


import io.nerdbyteslns.productcatalogservice.domains.Category;
import io.nerdbyteslns.productcatalogservice.domains.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    boolean existsByNameAndCategory(String name, Category category);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Product p WHERE p.name = :name AND p.category != :category")
    boolean NameExistsButWithDifferentCategory(String name, Category category);
}
