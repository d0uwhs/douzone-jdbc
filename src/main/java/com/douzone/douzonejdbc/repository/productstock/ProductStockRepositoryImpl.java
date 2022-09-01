package com.douzone.douzonejdbc.repository.productstock;

import com.douzone.douzonejdbc.dto.ProductDto;

import java.sql.Connection;
import java.util.List;

public class ProductStockRepositoryImpl implements ProductStockRepository {

    private static final ProductStockRepositoryImpl instance = new ProductStockRepositoryImpl();

    private ProductStockRepositoryImpl() {
    }

    public static ProductStockRepositoryImpl getInstance() {
        return instance;
    }

    @Override
    public List<ProductDto> findSelectedProducts(Connection connection, ProductDto productDto) {
        return null;
    }
}
