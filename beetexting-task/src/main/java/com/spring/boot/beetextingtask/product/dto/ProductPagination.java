package com.spring.boot.beetextingtask.product.dto;

import com.spring.boot.beetextingtask.product.entity.Product;

import java.util.List;

public class ProductPagination {
    private String response;
    private List<Product> data;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<Product> getData() {
        return data;
    }
    public void setData(List<Product> data) {
        this.data = data;
    }

}
