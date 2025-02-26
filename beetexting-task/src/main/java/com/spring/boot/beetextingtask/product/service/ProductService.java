package com.spring.boot.beetextingtask.product.service;

import com.spring.boot.beetextingtask.product.dto.ProductDto;
import com.spring.boot.beetextingtask.product.dto.ProductPagination;
import com.spring.boot.beetextingtask.product.entity.Product;
import com.spring.boot.beetextingtask.product.entity.ProductRepository;
import com.spring.boot.beetextingtask.util.AllPageResponse;
import com.spring.boot.beetextingtask.util.PageRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<Product> getProducts() {
        return this.productRepository.findAll();
    }

    public Product save(ProductDto input) {
        Product product = new Product();
        product.setName(input.name());
        product.setPrice(BigDecimal.valueOf(input.price()));
        product.setCategory(input.category());
        product.setStackQuantity(input.stackQuantity());
        this.productRepository.save(product);
        return product;
    }

    public List<Product> getProductsByCategoryAndName(String name, String category) {
        List<Product> productList = this.productRepository.findAll();
        List<Product> filteredProductList = new ArrayList<>();
        for (Product product : productList) {
            if (product.getCategory().equalsIgnoreCase(category)
                    || product.getName().equalsIgnoreCase(name)) {
                filteredProductList.add(product);
            }
        }
        return filteredProductList;
    }


    public List<Product> getProductsByPagination(int page, int size, String category, String name) {
        Page<Product> productList = this.productRepository.findByCategoryAndName(category,name, PageRequest.of(page, size));
       return productList.getContent();
    }


//    public List<Product> getProductsByPaginationByName(int page, int size, String name) {
//        Page<Product> productList = this.productRepository.findByName(name, PageRequest.of(page, size));
//        return productList.getContent();
//    }

    // I can make more productive and customizable based on our clients or product requirements


    public AllPageResponse findByPagination(PageRequestDto pageRequestDto){
        Page<Product> result;

        // sorting missing here on external input we can do that
        PageRequest pageRequest = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize(),
                Sort.by("name").ascending());

        if (pageRequestDto.getSearch() != null && !pageRequestDto.getSearch().isEmpty()){
            result = this.productRepository.findBySearchString(pageRequestDto.getSearch(), pageRequest);
        }else {
            result = this.productRepository.findAll(pageRequest);
        }

        if (result != null && !result.getContent().isEmpty()){
            List<Product> records = result.getContent();
            return new AllPageResponse(result.getTotalElements(),records);
        }else
            return new AllPageResponse();
    }


}
