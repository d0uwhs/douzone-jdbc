package com.douzone.douzonejdbc.controller;

import com.douzone.douzonejdbc.dto.ProductDto;
import com.douzone.douzonejdbc.services.DefaultProductServices;
import com.douzone.douzonejdbc.services.ProductService;

import java.util.List;

public class ProductController {
    private static final ProductController instance = new ProductController();

    private ProductController() {
    }

    public static ProductController getInstance() {
        return instance;
    }

    //    private final ProductService productService = new DefaultProductServices(ProductIORepositoryImpl.getInstance(), ProductStockRepositoryImpl.getInstance());
    private final ProductService productService = DefaultProductServices.getInstance();

    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    public List<ProductDto> getImportProducts() {
        return productService.getImportProducts();
    }

    public List<ProductDto> getExportProducts() {
        return productService.getExportProducts();
    }

    public List<ProductDto> getProducts(ProductDto productDto) {
        return productService.getProducts(productDto);
    }

    public Integer importProducts(ProductDto productDto) {
        return productService.importProduct(productDto);
    }

    public Integer exportProducts(ProductDto productDto) {
        return productService.exportProducts(productDto);
    }
}
