package com.javagroup3.javakiosk.service;

import com.javagroup3.javakiosk.dto.ProductDTO;
import com.javagroup3.javakiosk.entity.Member;
import com.javagroup3.javakiosk.entity.OrderRecord;
import com.javagroup3.javakiosk.entity.Product;
import com.javagroup3.javakiosk.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService { // 제품 목록을 DB 에서 불러와주는 클래스
    private final ProductRepository productRepository;

    public List<ProductDTO> findAll() { // 모든 항목 조회
        List<Product> productEntityList = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product productEntity: productEntityList) {
            productDTOList.add(ProductDTO.toDTO(productEntity));
        }
        return productDTOList;
    }

    public ProductDTO findById(Integer id) { // 제품 아이디 조회
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

    public List<Product> getList(){
        return this.productRepository.findAll();
    }
}
