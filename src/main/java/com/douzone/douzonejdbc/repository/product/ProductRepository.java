package com.douzone.douzonejdbc.repository.product;

import com.douzone.douzonejdbc.dto.ProductDto;

import java.sql.Connection;
import java.util.List;

public interface ProductRepository {
    List<ProductDto> findAllByProduct(Connection connection);

    List<ProductDto> findImportProduct(Connection connection);

    List<ProductDto> findExportProduct(Connection connection);

    Integer importProduct(Connection connection);

    Integer exportProduct(Connection connection);
}
