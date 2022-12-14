package com.douzone.douzonejdbc.repository.productio;

import com.douzone.douzonejdbc.dto.ProductDto;

import java.sql.Connection;
import java.util.List;

public interface ProductIORepository {
    List<ProductDto> findAllByProduct(Connection connection);

    List<ProductDto> findImportProduct(Connection connection);

    List<ProductDto> findExportProduct(Connection connection);

    Integer importProduct(Connection connection, ProductDto productDto);

    Integer exportProduct(Connection connection, ProductDto productDto);
}
