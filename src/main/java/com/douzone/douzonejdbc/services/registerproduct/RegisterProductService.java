package com.douzone.douzonejdbc.services.registerproduct;

import com.douzone.douzonejdbc.dto.ProductDto;
import com.douzone.douzonejdbc.exception.product.ProductNotFoundException;

import java.sql.Connection;
import java.util.List;

public interface RegisterProductService {

    List<ProductDto> getAllRegisteredProducts();

    List<ProductDto> findByRegisteredProducts(String productId) throws ProductNotFoundException;

    Integer registerProduct(ProductDto productDto);

    Integer removeProduct(String productId);

    Integer editProduct(ProductDto productDto);
}
