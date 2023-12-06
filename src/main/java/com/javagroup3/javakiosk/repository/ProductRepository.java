package com.javagroup3.javakiosk.repository;

import com.javagroup3.javakiosk.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT * FROM product ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Product findRandomProduct();

    @Query(value = "SELECT * FROM product WHERE product.category = 'beef'", nativeQuery = true)
    List<Product> findBeefProduct();

    @Query(value = "SELECT * FROM product WHERE product.category = 'pizza'", nativeQuery = true)
    List<Product> findPizzaProduct();

    @Query(value = "SELECT * FROM product WHERE product.category = 'pasta'", nativeQuery = true)
    List<Product> findPastaProduct();

    @Query(value = "SELECT * FROM product WHERE product.category = 'risotto'", nativeQuery = true)
    List<Product> findRisottoProduct();

    @Query(value = "SELECT * FROM product WHERE product.category = 'dessert'", nativeQuery = true)
    List<Product> findDessertProduct();
}