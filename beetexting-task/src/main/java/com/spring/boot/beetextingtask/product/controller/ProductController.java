package com.spring.boot.beetextingtask.product.controller;

import com.spring.boot.beetextingtask.product.dto.ProductDto;
import com.spring.boot.beetextingtask.product.dto.ProductPagination;
import com.spring.boot.beetextingtask.product.entity.Product;
import com.spring.boot.beetextingtask.product.service.ProductService;
import com.spring.boot.beetextingtask.util.AllPageResponse;
import com.spring.boot.beetextingtask.util.PageRequestDto;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@OpenAPI30
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    // retrieve all records from db
    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    // save the new product record
    @PostMapping("/saveProduct")
    public Product saveProduct(@RequestBody ProductDto product) {
        return this.productService.save(product);
    }

    // get the list of record which is match of any give category or name values
    @GetMapping(value = "/productsByCategoryAndName")
    public List<Product> getProductsByCategoryAndName(@RequestParam String name, @RequestParam String category) {
        return this.productService.getProductsByCategoryAndName(name, category);
    }

    // simple pagination works on inbuilt procedural implementation.
    @GetMapping(value = "/productsPagination")
    public List<Product> getProductsByPagination(@RequestParam int page, @RequestParam int size,
                                                           @RequestParam String category, @RequestParam String name) {
        return this.productService.getProductsByPagination(page, size,category, name);
    }

//    @GetMapping(value = "/productsPaginationByName")
//    public List<Product> getProductsByPaginationByName(@RequestParam int page, @RequestParam int size, @RequestParam String name) {
//        return this.productService.getProductsByPaginationByName(page, size,name);
//    }

    // more customizable pagination
    @PutMapping(value = "/findProductsByPagination")
    public AllPageResponse findBySearchString(@RequestBody PageRequestDto pageRequestDto) {
        return this.productService.findByPagination(pageRequestDto);
    }

}
