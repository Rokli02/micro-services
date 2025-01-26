package hu.me.microservice.product.controller;

import hu.me.microservice.product.entity.Product;
import hu.me.microservice.product.model.NewProductDTO;
import hu.me.microservice.product.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")

public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<Product> getProducts(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(required = false) String search,
                                     @RequestParam(required = false) Boolean onlyActive) {
        return productService.getProducts(PageRequest.of(page, size), search, onlyActive);
    }

    @GetMapping("/{id}")
    public Optional<Product> getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PostMapping("/batch")
    public List<Product> getByIds(@RequestBody List<Long> ids) {
        return productService.getByIds(ids);
    }

    @PostMapping
    public Product save(@RequestBody NewProductDTO newProduct) {
        return productService.save(newProduct);
    }

    @PutMapping("/{id}")
    public boolean update(@PathVariable Long id, @RequestBody Product product) {
        return productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return productService.delete(id);
    }
}
