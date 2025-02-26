package com.spring.boot.beetextingtask.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String category;
    private BigDecimal price;
    private Long stackQuantity;

    public Product() {
        super();
    }

    public Product(Integer id, String name, String category, BigDecimal price, Long stackQuantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stackQuantity = stackQuantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getStackQuantity() {
        return stackQuantity;
    }

    public void setStackQuantity(Long stackQuantity) {
        this.stackQuantity = stackQuantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", stackQuantity=" + stackQuantity +
                '}';
    }
}
