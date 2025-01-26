package hu.me.microservice.product.service;

import hu.me.microservice.product.entity.Product;
import hu.me.microservice.product.model.NewProductDTO;
import hu.me.microservice.product.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> getProducts(Pageable pageable, String search, Boolean onlyActive) {
        return productRepository.findProducts(pageable, search, onlyActive);
    }

    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getByIds(List<Long> ids) {
        return productRepository.findByIdIn(ids);
    }

    public Product save(NewProductDTO newProduct) {
        Product product = new Product();
        product.setName(newProduct.getName());
        product.setDescription(newProduct.getDescription());
        product.setBasePrice(newProduct.getBasePrice());
        product.setDiscount(newProduct.getDiscount());
        product.setCreatedAt(LocalDateTime.now());
        return productRepository.save(product);
    }

    public boolean update(Long id, Product updatedProduct) {
        return productRepository.findById(id).map(existingProduct -> {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setBasePrice(updatedProduct.getBasePrice());
            existingProduct.setDiscount(updatedProduct.getDiscount());
            existingProduct.setActive(updatedProduct.isActive());
            productRepository.save(existingProduct);
            return true;
        }).orElse(false);
    }

    public boolean delete(Long id) {
        return productRepository.findById(id).map(product -> {
            product.setActive(false);
            productRepository.save(product);
            return true;
        }).orElse(false);
    }
}
