package com.douzone.douzonejdbc.controller.product;

import com.douzone.douzonejdbc.dto.ProductDto;
import com.douzone.douzonejdbc.exception.product.ProductNotEnoughException;
import com.douzone.douzonejdbc.services.product.DefaultProductService;
import com.douzone.douzonejdbc.services.product.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductController {
    private static final ProductController instance = new ProductController();
    private final ProductService productService = DefaultProductService.getInstance();

    private ProductController() {
    }

    public static ProductController getInstance() {
        return instance;
    }

    //    private final ProductService productService = new DefaultProductServices(ProductIORepositoryImpl.getInstance(), ProductStockRepositoryImpl.getInstance());

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

    public Integer exportProducts(ProductDto productDto) throws ProductNotEnoughException, SQLException {
        return productService.exportProducts(productDto);
    }
}
