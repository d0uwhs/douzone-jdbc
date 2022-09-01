package com.douzone.douzonejdbc.repository.productstock;

import com.douzone.douzonejdbc.dto.ProductDto;

import java.sql.Connection;
import java.util.List;

public interface ProductStockRepository {
    List<ProductDto> findSelectedProducts(Connection connection, ProductDto productDto);

    List<ProductDto> findAllRegisteredProducts(Connection connection);

    Integer registerProduct(Connection connection, ProductDto productDto);

    Integer editProduct(Connection connection, ProductDto productDto);

    Integer removeProduct(Connection connection, String productId);
    List<ProductDto> findByRegisteredProducts (Connection connection, String productId);
}
