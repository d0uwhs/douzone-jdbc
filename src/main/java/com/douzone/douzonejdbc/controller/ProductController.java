package com.douzone.douzonejdbc.controller;

import com.douzone.douzonejdbc.dto.ProductDto;
import com.douzone.douzonejdbc.repository.product.ProductRepositoryImpl;
import com.douzone.douzonejdbc.services.DefaultProductServices;
import com.douzone.douzonejdbc.services.ProductService;

import java.util.List;

public class ProductController {
    private final ProductService productService = new DefaultProductServices(new ProductRepositoryImpl());

    public List<ProductDto> getAllProducts(){
        return productService.getAllProducts();
    }
    public List<ProductDto> getImportProducts(){
        return productService.getImportProducts();
    }
    public List<ProductDto> getExportProducts(){
        return productService.getExportProducts();
    }
}
