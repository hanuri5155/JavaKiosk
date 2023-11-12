package com.javagroup3.javakiosk.service;

import com.javagroup3.javakiosk.dto.ProductDTO;
import com.javagroup3.javakiosk.entity.Member;
import com.javagroup3.javakiosk.entity.Product;
import com.javagroup3.javakiosk.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductDTO> findAll() {
        List<Product> productEntityList = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product productEntity: productEntityList) {
            productDTOList.add(ProductDTO.toDTO(productEntity));
        }
        return productDTOList;
    }

    public ProductDTO findById(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            return ProductDTO.toDTO(optionalProduct.get());
        }else{
            return null;
        }
    }

    public void ubdate(ProductDTO productDTO) {
        productRepository.save(Product.toUpdateProductEntity(productDTO));
    }
}
