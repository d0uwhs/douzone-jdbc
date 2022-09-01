package com.douzone.douzonejdbc.repository.productstock;

import com.douzone.douzonejdbc.dto.ProductDto;

import java.sql.Connection;
import java.util.List;

public interface ProductStockRepository {
    List<ProductDto> findSelectedProducts(Connection connection, ProductDto productDto);
}
