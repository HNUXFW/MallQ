package com.example.mallq.repository;

import com.example.mallq.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Modifying//JPA支持查询操作，如果想执行UPDATE和DELETE操作，需要加上
    @Query("UPDATE Product p SET p.stock = p.stock - :quantity WHERE p.id = :id AND p.stock >= :quantity")
    int decreaseStock(Long id, Integer quantity);
} 