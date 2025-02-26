package com.spring.boot.beetextingtask.product.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {


    Pageable pageable = PageRequest.of(0, 10);
    //Page<Product> findByCategory(String category, Pageable pageable);

    Page<Product> findByName(String name, Pageable pageable);

    Page<Product> findByCategoryAndName(String category, String name, Pageable pageable);


    @Query("SELECT p FROM Product p WHERE p.name LIKE CONCAT('%', :search, '%') " +
            "OR p.category LIKE CONCAT('%', :search, '%')")
    Page<Product> findBySearchString(String search, Pageable pageable);
}
