package com.javagroup3.javakiosk.service;

import com.javagroup3.javakiosk.entity.Product;
import com.javagroup3.javakiosk.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getList(){
        return this.productRepository.findAll();
    }
    public void AddProduct(Product product){
        this.productRepository.save(product);
    }
}
