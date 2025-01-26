package hu.me.microservice.product.repository;

import hu.me.microservice.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE (:search IS NULL OR p.name LIKE %:search%) AND (:onlyActive IS NULL OR p.isActive = :onlyActive)")
    Page<Product> findProducts(Pageable pageable, String search, Boolean onlyActive);

    List<Product> findByIdIn(List<Long> ids);
}
