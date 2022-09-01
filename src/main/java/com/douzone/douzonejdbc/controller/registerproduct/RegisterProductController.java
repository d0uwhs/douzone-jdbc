package com.douzone.douzonejdbc.controller.registerproduct;

import com.douzone.douzonejdbc.dto.ProductDto;
import com.douzone.douzonejdbc.exception.product.ProductNotFoundException;
import com.douzone.douzonejdbc.services.registerproduct.DefaultRegisterProductService;
import com.douzone.douzonejdbc.services.registerproduct.RegisterProductService;

import java.util.List;

public class RegisterProductController {

    private final RegisterProductService registerProductService = DefaultRegisterProductService.getInstance();


    private static final RegisterProductController instance = new RegisterProductController();


    private RegisterProductController() {
    }

    public static RegisterProductController getInstance() {
        return instance;
    }

    public List<ProductDto> getAllRegisteredProducts() {
        return registerProductService.getAllRegisteredProducts();
    }

    public Integer registerProduct(ProductDto productDto) {
        return registerProductService.registerProduct(productDto);
    }

    public List<ProductDto> findByRegisteredProducts(String productId) throws ProductNotFoundException {
        return registerProductService.findByRegisteredProducts(productId);
    }

    public Integer editProduct(ProductDto productDto){
        return registerProductService.editProduct(productDto);

    }

    public Integer removeProduct(String productId) {
        return registerProductService.removeProduct(productId);
    }
}
